#!/usr/bin/env python3
"""Synapse MCP server — exposes the Synapse base-class catalog to any MCP-capable
AI agent (Claude Code, etc.) so it generates correct, idiomatic Synapse code.

Zero third-party dependencies: speaks the Model Context Protocol over stdio using
newline-delimited JSON-RPC 2.0, reading ground truth from ../catalog.json.

Tools:
  - list_base_classes : list operations/base classes for a layer family (filterable)
  - scaffold_operation: return copy-ready Controller+Service(+Request/Response) skeletons for an operation
  - validate_module   : statically check a generated module dir against Synapse conventions

Run directly for a smoke test:  python3 synapse_mcp.py --selftest
"""
import json
import os
import re
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
CATALOG_PATH = os.path.join(HERE, "..", "catalog.json")
PROTOCOL_VERSION = "2024-11-05"
SERVER_INFO = {"name": "synapse-mcp", "version": "0.1.0"}


def load_catalog():
    with open(CATALOG_PATH, "r", encoding="utf-8") as fh:
        return json.load(fh)


# --------------------------------------------------------------------------- #
# Tool implementations
# --------------------------------------------------------------------------- #
def _find_operation(catalog, family, operation_id):
    fam = catalog["families"].get(family)
    if not fam:
        raise ValueError(
            "unknown family '%s'; known: %s" % (family, ", ".join(catalog["families"]))
        )
    for op in fam.get("operations", []):
        if op["id"] == operation_id:
            return fam, op
    known = ", ".join(o["id"] for o in fam.get("operations", []))
    raise ValueError(
        "unknown operation '%s' in family '%s'; known: %s" % (operation_id, family, known)
    )


def tool_list_base_classes(catalog, args):
    family = args.get("family")
    families = [family] if family else list(catalog["families"])
    out = {}
    for fam_name in families:
        fam = catalog["families"].get(fam_name)
        if not fam or "operations" not in fam:
            continue
        out[fam_name] = {
            "basePackage": fam.get("basePackage"),
            "artifact": fam.get("artifact"),
            "operations": [
                {
                    "id": op["id"],
                    "http": "%s %s" % (op.get("httpMethod", "?"), op.get("path", "")),
                    "controller": op["controller"]["class"],
                    "service": op["service"]["class"],
                    "override": op["service"]["override"],
                }
                for op in fam["operations"]
            ],
        }
    return out


def _camel(name):
    return re.sub(r"[^0-9a-zA-Z]", "", name[:1].upper() + name[1:])


def tool_scaffold_operation(catalog, args):
    family = args.get("family", "imperativeRest")
    operation_id = args["operation"]
    entity = _camel(args.get("entity", "Resource"))
    pkg = args.get("packageName", "com.example.app")
    base_url = args.get("baseUrl", "/v1/" + entity.lower() + "s")

    fam, op = _find_operation(catalog, family, operation_id)
    ctrl = op["controller"]["class"]
    svc = op["service"]["class"]
    base_pkg = fam["basePackage"]
    op_cap = _camel(operation_id)
    reactive = family == "reactiveRest"

    req_cls = "%s%sRequest" % (entity, op_cap)
    resp_cls = "%s%sResponse" % (entity, op_cap)
    ctrl_cls = "%s%sController" % (entity, op_cap)
    svc_cls = "%s%sService" % (entity, op_cap)

    # Determine generic argument list the concrete class passes to the base.
    needs_req = "I extends" in (op["controller"].get("generics") or "") or "I extends" in (
        op["service"].get("generics") or ""
    )
    needs_resp = "O extends" in (op["controller"].get("generics") or "")

    ctrl_generics = []
    if needs_req:
        ctrl_generics.append(req_cls)
    if needs_resp:
        ctrl_generics.append(resp_cls)
    ctrl_generics.append(svc_cls)
    ctrl_decl = "%s<%s>" % (ctrl, ", ".join(ctrl_generics)) if ctrl_generics else ctrl

    svc_generics = []
    if needs_req:
        svc_generics.append(req_cls)
    if needs_resp:
        svc_generics.append(resp_cls)
    svc_decl = "%s<%s>" % (svc, ", ".join(svc_generics)) if svc_generics else svc

    files = {}

    files["controller/%s.java" % ctrl_cls] = (
        "package %s.controller;\n\n"
        "import %s.controller.%s;\n"
        "import org.springframework.web.bind.annotation.RequestMapping;\n"
        "import org.springframework.web.bind.annotation.RestController;\n\n"
        "@RestController\n"
        "@RequestMapping(\"%s\")\n"
        "public class %s extends %s {\n}\n"
        % (pkg, base_pkg, ctrl, base_url, ctrl_cls, ctrl_decl)
    )

    override = op["service"]["override"]
    if reactive:
        body = "        return reactor.core.publisher.Mono.empty(); // TODO business logic"
        if "Flux<" in override:
            body = "        return reactor.core.publisher.Flux.empty(); // TODO business logic"
    elif override.startswith("protected void"):
        body = "        // TODO business logic"
    elif "Page<" in override:
        body = "        return org.springframework.data.domain.Page.empty(); // TODO business logic"
    else:
        body = "        return new %s(); // TODO business logic" % resp_cls

    files["service/%s.java" % svc_cls] = (
        "package %s.service;\n\n"
        "import %s.service.%s;\n"
        "import org.springframework.http.HttpHeaders;\n"
        "import org.springframework.stereotype.Service;\n\n"
        "@Service\n"
        "public class %s extends %s {\n\n"
        "    @Override\n"
        "    %s {\n"
        "%s\n"
        "    }\n}\n"
        % (pkg, base_pkg, svc, svc_cls, svc_decl, override, body)
    )

    if needs_req:
        files["model/%s.java" % req_cls] = (
            "package %s.model;\n\n"
            "import %s.model.BaseServiceRequest;\n"
            "import jakarta.validation.constraints.NotBlank;\n\n"
            "public class %s implements BaseServiceRequest {\n"
            "    @NotBlank private String name; // TODO real fields\n"
            "    public String getName() { return name; }\n"
            "    public void setName(String name) { this.name = name; }\n}\n"
            % (pkg, base_pkg, req_cls)
        )
    if needs_resp:
        files["model/%s.java" % resp_cls] = (
            "package %s.model;\n\n"
            "import %s.model.BaseServiceResponse;\n\n"
            "public class %s extends BaseServiceResponse {\n"
            "    private String name; // TODO real fields; id inherited via setId()\n"
            "    public String getName() { return name; }\n"
            "    public void setName(String name) { this.name = name; }\n}\n"
            % (pkg, base_pkg, resp_cls)
        )

    return {
        "family": family,
        "operation": operation_id,
        "http": "%s %s%s" % (op.get("httpMethod"), base_url, op.get("path", "")),
        "appConfigHint": fam.get("appConfig"),
        "files": files,
    }


def tool_validate_module(catalog, args):
    root = args["modulePath"]
    findings = []
    java_files = []
    for dirpath, _dirs, names in os.walk(root):
        if os.sep + "test" + os.sep in dirpath:
            continue
        for n in names:
            if n.endswith(".java"):
                java_files.append(os.path.join(dirpath, n))

    if not java_files:
        return {"ok": False, "findings": [{"level": "error", "msg": "no .java files under " + root}]}

    ctrl_bases = set()
    svc_bases = set()
    for fam in catalog["families"].values():
        for op in fam.get("operations", []):
            ctrl_bases.add(op["controller"]["class"])
            svc_bases.add(op["service"]["class"])

    for path in java_files:
        with open(path, "r", encoding="utf-8") as fh:
            src = fh.read()
        rel = os.path.relpath(path, root)
        is_ctrl = "@RestController" in src
        is_svc = re.search(r"@Service\b", src) is not None

        if is_ctrl:
            if not any(("extends " + b) in src for b in ctrl_bases):
                findings.append({"level": "error", "file": rel,
                                 "msg": "@RestController does not extend a Synapse Base*Controller"})
            if "controller" not in rel.replace("\\", "/").split("/"):
                findings.append({"level": "warn", "file": rel,
                                 "msg": "controller not in a 'controller' package"})
            if re.search(r"@(Post|Get|Put|Delete|Request)Mapping", src) and "class " in src:
                # mappings on the class beyond @RequestMapping base path are a smell
                if re.search(r"@(Post|Get|Put|Delete)Mapping", src):
                    findings.append({"level": "warn", "file": rel,
                                     "msg": "verb @*Mapping found; base controller already maps the verb"})
        if is_svc and any(("extends " + b) in src for b in svc_bases):
            if "execute" not in src:
                findings.append({"level": "error", "file": rel,
                                 "msg": "Synapse service does not override an execute* method"})
        if "implements BaseServiceRequest" in src and "extends BaseServiceRequest" in src:
            findings.append({"level": "error", "file": rel,
                             "msg": "BaseServiceRequest is an interface — use 'implements'"})
        if re.search(r"class\s+\w+\s+implements\s+BaseServiceResponse", src):
            findings.append({"level": "error", "file": rel,
                             "msg": "BaseServiceResponse is an abstract class — use 'extends'"})
        if "@RestControllerAdvice" in src:
            findings.append({"level": "warn", "file": rel,
                             "msg": "custom @RestControllerAdvice; Synapse auto-registers ControllerExceptionHandler"})

    ok = not any(f["level"] == "error" for f in findings)
    return {"ok": ok, "filesScanned": len(java_files), "findings": findings}


TOOLS = {
    "list_base_classes": {
        "fn": tool_list_base_classes,
        "description": "List Synapse base classes/operations for a layer family (imperativeRest, reactiveRest). Returns controller, service, and the protected abstract method to override.",
        "schema": {
            "type": "object",
            "properties": {"family": {"type": "string", "description": "Optional family filter; omit for all."}},
        },
    },
    "scaffold_operation": {
        "fn": tool_scaffold_operation,
        "description": "Generate copy-ready Controller + Service (+ Request/Response) skeletons for one Synapse operation.",
        "schema": {
            "type": "object",
            "required": ["operation", "entity"],
            "properties": {
                "family": {"type": "string", "default": "imperativeRest"},
                "operation": {"type": "string", "description": "Operation id, e.g. create, readMono, readPoly, getMono, update, delete."},
                "entity": {"type": "string", "description": "Domain entity name, e.g. Book."},
                "packageName": {"type": "string", "default": "com.example.app"},
                "baseUrl": {"type": "string", "description": "Base request mapping, e.g. /v1/books."},
            },
        },
    },
    "validate_module": {
        "fn": tool_validate_module,
        "description": "Statically validate a generated module directory against Synapse conventions (base-class extension, execute* override, request/response inheritance, no custom advice).",
        "schema": {
            "type": "object",
            "required": ["modulePath"],
            "properties": {"modulePath": {"type": "string", "description": "Path to the module's source root to scan."}},
        },
    },
}


# --------------------------------------------------------------------------- #
# JSON-RPC / MCP plumbing
# --------------------------------------------------------------------------- #
def _result(req_id, result):
    return {"jsonrpc": "2.0", "id": req_id, "result": result}


def _error(req_id, code, message):
    return {"jsonrpc": "2.0", "id": req_id, "error": {"code": code, "message": message}}


def handle(catalog, msg):
    method = msg.get("method")
    req_id = msg.get("id")
    params = msg.get("params") or {}

    if method == "initialize":
        return _result(req_id, {
            "protocolVersion": PROTOCOL_VERSION,
            "capabilities": {"tools": {}},
            "serverInfo": SERVER_INFO,
        })
    if method in ("notifications/initialized", "initialized"):
        return None
    if method == "tools/list":
        return _result(req_id, {
            "tools": [
                {"name": name, "description": spec["description"], "inputSchema": spec["schema"]}
                for name, spec in TOOLS.items()
            ]
        })
    if method == "tools/call":
        name = params.get("name")
        arguments = params.get("arguments") or {}
        spec = TOOLS.get(name)
        if not spec:
            return _error(req_id, -32601, "unknown tool: %s" % name)
        try:
            payload = spec["fn"](catalog, arguments)
            return _result(req_id, {"content": [{"type": "text", "text": json.dumps(payload, indent=2)}]})
        except Exception as exc:  # surface as tool error, not transport error
            return _result(req_id, {
                "isError": True,
                "content": [{"type": "text", "text": "%s: %s" % (type(exc).__name__, exc)}],
            })
    if req_id is not None:
        return _error(req_id, -32601, "unknown method: %s" % method)
    return None


def serve():
    catalog = load_catalog()
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        try:
            msg = json.loads(line)
        except json.JSONDecodeError:
            continue
        response = handle(catalog, msg)
        if response is not None:
            sys.stdout.write(json.dumps(response) + "\n")
            sys.stdout.flush()


def selftest():
    catalog = load_catalog()
    assert tool_list_base_classes(catalog, {})["imperativeRest"]["operations"], "list failed"
    sc = tool_scaffold_operation(catalog, {"operation": "create", "entity": "Book", "packageName": "com.acme.book"})
    assert any("BookCreateController" in k for k in sc["files"]), "scaffold controller missing"
    assert "executeCreate" in next(v for k, v in sc["files"].items() if "Service" in k), "override missing"
    for m in ("initialize", "tools/list"):
        assert handle(catalog, {"jsonrpc": "2.0", "id": 1, "method": m}), m + " failed"
    call = handle(catalog, {"jsonrpc": "2.0", "id": 2, "method": "tools/call",
                            "params": {"name": "list_base_classes", "arguments": {}}})
    assert call["result"]["content"], "tools/call failed"
    print("selftest OK")


if __name__ == "__main__":
    if "--selftest" in sys.argv:
        selftest()
    else:
        serve()
