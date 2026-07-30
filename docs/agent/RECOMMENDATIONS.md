# Adapting Synapse for AI agents — recommendations

This document captures changes that make the Synapse framework reliably consumable by AI coding agents.
It is split into **(A) what was implemented on this branch** (additive, safe) and **(B) proposals that
touch the published API or build and therefore need `@americanexpress/synapse-team` review before
shipping** — because they would emit deprecation warnings or break downstream consumers on Maven Central.

The throughline: agents fail when they must *infer* signatures and conventions from source. Every item
below either gives them machine-readable ground truth, removes ambiguity, or adds a deterministic
verify loop.

---

## A. Implemented on this branch (additive)

| # | Change | Files |
|---|---|---|
| A1 | **Machine-readable catalog** of every base class, operation, override method, generics, HTTP verb/path, config triple, ErrorCodes, archetypes. The single source of truth for the skill + MCP server. | `.synapse/catalog.json`, `.synapse/catalog.schema.json` |
| A2 | **MCP server** (Python stdlib, zero deps) exposing `list_base_classes`, `scaffold_operation`, `validate_module` to any MCP client. | `.synapse/mcp/`, `.mcp.json` |
| A3 | **Provider-agnostic agent + skill** (`synapse-engineer`, plain Markdown) with verified base-class decision tables and skeletons; any AI runtime can load them. | `.synapse/agents/`, `.synapse/skills/` |
| A4 | **Archetype catalog version fix** — `archetype/archetype-catalog.xml` was pinned to `0.3.32-SNAPSHOT` while the project is `0.4.28-SNAPSHOT`, so headless `archetype:generate` from the catalog resolved the wrong (often unavailable) version. Synced to `0.4.28-SNAPSHOT`. | `archetype/archetype-catalog.xml` |
| A5 | **`@implSpec` Javadoc** demonstrated on the create base classes — self-describing "extend me, override `executeCreate`, don't add `@PostMapping`" guidance agents read directly. | `BaseCreateController.java`, `BaseCreateService.java` |
| A6 | **ArchUnit convention tests** template — a deterministic verify loop. | `docs/agent/archunit/` |
| A7 | **Repo CLAUDE.md** orienting any agent to the monorepo. | `CLAUDE.md` |

---

## B. Proposals requiring team review

### B1 — Generate `catalog.json` from source (close the drift gap)

`catalog.json` (A1) is currently hand-maintained from verified source. That is exactly the kind of file
that silently rots. Make it a build output so it can never disagree with the code:

- Option 1 (lightweight): a small Maven plugin / `exec` step that scans the `service`, `data`, and
  `client` modules for `Base*Controller`/`Base*Service`/`Base*Client` and emits `catalog.json` during
  `package`, failing the build if the committed file differs.
- Option 2 (richer): an annotation processor driven by a new `@SynapseOperation(verb=…, path=…)`
  meta-annotation on each base class, which both documents the class and feeds the generator.

Until B1 lands, treat `catalog.json` as needing a refresh whenever a base class signature changes, and
keep the `bump-version` workflow honest (see B5).

### B2 — Resolve the dual imperative API families (biggest ambiguity)

There are **two** parallel imperative hierarchies and an agent cannot tell which to use:

- `io.americanexpress.synapse.service.rest.*` — granular: `BaseCreateController` + `BaseCreateService`
  with `executeCreate/executeRead/executeUpdate/executeDelete`. **All samples use this.**
- `io.americanexpress.synapse.api.rest.imperative.*` — unified: `BaseService<I,O>.execute` /
  `doExecute`, `PageResponse<O>`, different paths (`/inquiry-results`, `/multiple-results`).

Mixing them compiles but misbehaves. Recommendation: pick one as canonical and signal the other.

```diff
// api/synapse-api-rest-imperative/.../controller/BaseCreateImperativeRestController.java
+/**
+ * @deprecated Experimental unified API family. Prefer the granular
+ * {@code io.americanexpress.synapse.service.rest.controller.BaseCreateController}
+ * used by all reference samples. The two families must not be mixed within one service.
+ */
+@Deprecated(since = "0.4.28", forRemoval = false)
 public abstract class BaseCreateImperativeRestController< ... >
```

If `api.rest.imperative` is in fact the intended future direction, do the inverse and migrate the
samples — but the repository should express a single recommended path either way. (`@Deprecated` is a
published-API signal, hence team review.)

### B3 — Normalize per-DB datasource property keys

Property keys differ by data module, which is a frequent silent misconfiguration for agents:

- `synapse-data-postgres` / `-jdbc`: `spring.datasource.{url,username,password,driver-class-name}`
- `synapse-data-mysql`: `spring.mysql.datasource.{url,username,password,port}`  ← non-standard prefix

Recommendation: standardize all relational modules on the Spring Boot convention `spring.datasource.*`
(optionally `spring.datasource.<db>.*` for multi-datasource), with a one-release deprecation that reads
the old keys and logs a warning. This is a behavioral/config-contract change → team review + CHANGELOG.

### B4 — Roll `@implSpec` out across all base classes

A5 demonstrates the pattern on the create pair. Apply the same self-describing Javadoc (which extension
point to override, what not to annotate, how to signal errors) to every `Base*Controller`/`Base*Service`
/`Base*ReactiveController`/`Base*Client` and to `BaseServiceRequest` (state it's an interface →
`implements`) and `BaseServiceResponse` (abstract, carries `id` → `extends`). Pure documentation, but
broad — best done as one reviewed sweep.

### B5 — Make `bump-version` also bump the archetype catalog

`.github/workflows/bump-version.yml` runs `mvn versions:set` which does **not** update
`archetype/archetype-catalog.xml` (a plain XML list) — that is how the A4 drift happened. Add a step to
rewrite the catalog versions (and ideally `.synapse/catalog.json`'s `frameworkVersion`) as part of the
bump, or tokenize the catalog and filter it at build time. Prevents A4 from recurring.

### B6 — Promote the ArchUnit rules into a shared module

Move `docs/agent/archunit/SynapseConventionTest.java` into a new `synapse-architecture-test` module that
application teams depend on (test scope), so the conventions are versioned and enforced everywhere rather
than copy-pasted. Wire it into the reactor so CI compiles/runs it.

### B7 — Scaffold tests by default

`synapse-service-test` ships `BaseControllerTest` / `BaseControllerIT` / `Base*ControllerUnitTest`, but
the archetypes and skill don't always emit a test. Have both always generate a test extending the right
base, so agent-generated modules ship green, not just with `main` code.

---

## Priority

1. **B2** (resolve dual family) and **B1** (generate the catalog) — structural correctness-by-construction.
2. **B5**, **B7**, **B6** — cheap, high-yield, prevent regressions.
3. **B3**, **B4** — valuable normalization, larger surface.
