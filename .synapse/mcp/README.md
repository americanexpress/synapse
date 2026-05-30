# Synapse MCP server

A zero-dependency [Model Context Protocol](https://modelcontextprotocol.io) server that exposes the
Synapse base-class catalog (`.synapse/catalog.json`) to AI coding agents, so any MCP-capable runtime
generates correct, idiomatic Synapse code instead of re-deriving signatures from source.

It complements the provider-agnostic `synapse-engineer` agent/skill in `.synapse/agents/` and
`.synapse/skills/` (plain Markdown any runtime can load): those carry the human-readable recipe, while
this server exposes the same catalog as callable tools to **any** MCP client (Claude Code, Cursor,
custom agents, …).

## Requirements

Python 3.8+. No pip installs — it uses only the standard library and speaks JSON-RPC 2.0 over stdio.

## Tools

| Tool | Purpose | Key args |
|---|---|---|
| `list_base_classes` | List operations/base classes for a layer family, with the `execute*` method to override. | `family` (optional: `imperativeRest`, `reactiveRest`) |
| `scaffold_operation` | Return copy-ready Controller + Service (+ Request/Response) skeletons for one operation. | `operation`, `entity`, `packageName`, `baseUrl`, `family` |
| `validate_module` | Statically check a generated module dir against Synapse conventions. | `modulePath` |

`validate_module` flags: `@RestController` not extending a `Base*Controller`; a Synapse `@Service` that
never overrides `execute*`; `implements BaseServiceResponse` (it's abstract → `extends`);
`extends BaseServiceRequest` (it's an interface → `implements`); custom `@RestControllerAdvice`
(redundant — the layer config auto-registers `ControllerExceptionHandler`); verb `@*Mapping` on a
controller (the base already maps the verb).

## Smoke test

```bash
python3 .synapse/mcp/synapse_mcp.py --selftest      # prints "selftest OK"
```

Manual JSON-RPC round trip:

```bash
printf '%s\n' \
  '{"jsonrpc":"2.0","id":1,"method":"initialize","params":{}}' \
  '{"jsonrpc":"2.0","id":2,"method":"tools/call","params":{"name":"scaffold_operation","arguments":{"operation":"create","entity":"Book","packageName":"com.acme.book","baseUrl":"/v1/books"}}}' \
  | python3 .synapse/mcp/synapse_mcp.py
```

## Registering with an MCP client

A ready-made `.mcp.json` lives at the repo root (Claude Code auto-discovers it). For other clients,
point them at the command:

```json
{
  "mcpServers": {
    "synapse": {
      "command": "python3",
      "args": [".synapse/mcp/synapse_mcp.py"]
    }
  }
}
```

Use an absolute path to `synapse_mcp.py` if the client's working directory isn't the repo root.

## Keeping the catalog honest

The server reads `.synapse/catalog.json`. That file is currently hand-maintained from verified source.
To guarantee it never drifts from the framework, generate it during the build — see
`docs/agent/RECOMMENDATIONS.md` (#1, catalog-generation Maven plugin).
