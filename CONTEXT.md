# CONTEXT.md

> **Living context for AI agents working on Synapse.** This is the fast-changing "where things
> stand right now" companion to `CLAUDE.md` (stable orientation) and `.synapse/catalog.json`
> (machine-readable base-class facts). Read this first to get current; update it when you change
> anything it describes.
>
> **Maintained by the `synapse-engineer` agent.** Keeping this file current is part of that agent's
> job — see [Update protocol](#update-protocol). Any agent or human that changes the framework
> surface, the tooling, or the open-items list below should update this file in the same change.

_Last updated: 2026-05-30 · reflects catalog `catalogVersion: 1` · framework `0.4.28-SNAPSHOT`_

---

## Current snapshot

- **Framework:** `io.americanexpress.synapse` `0.4.28-SNAPSHOT`, Java 21, Spring Boot 3.5.x, `jakarta.*`.
- **Default branch:** `develop`. **Active branch:** `chore/claude-md-and-dev` → **PR #481** (base `develop`).
- **CI status:** CodeQL is **red due to a GitHub Actions billing lock** on the org (jobs never start;
  not a code problem). Maven/CircleCI build not verifiable in the dev sandbox (offline BOMs). See
  [Open items](#open-items).
- **What PR #481 adds:** the AI-agent adaptation layer (this file, `.synapse/` tooling, `docs/agent/`),
  the archetype-catalog version fix, and `@implSpec` Javadoc on the create base classes.

## AI-agent tooling map (what exists, where)

| Path | Role |
|---|---|
| `.synapse/catalog.json` (+ `catalog.schema.json`) | Authoritative machine-readable base-class catalog. Source of truth. |
| `.synapse/mcp/` (+ `/.mcp.json`) | Zero-dep MCP server: `list_base_classes` / `scaffold_operation` / `validate_module`. |
| `.synapse/agents/synapse-engineer.md` | Provider-agnostic agent persona for building Synapse code. |
| `.synapse/skills/synapse-engineer/SKILL.md` | Step-by-step scaffolding recipe + skeletons. |
| `docs/agent/RECOMMENDATIONS.md` | Framework-level proposals needing `@americanexpress/synapse-team` review. |
| `docs/agent/archunit/` | ArchUnit convention tests (template; build-unverified). |
| `CLAUDE.md` | Stable repo orientation (build, architecture, CI, conventions). |

All AI tooling is under provider-agnostic `.synapse/` / `docs/agent/`. `.claude/` is gitignored
(Claude Code users symlink `.claude/{agents,skills}` → `../../.synapse/{agents,skills}`).

## Critical facts agents must not get wrong

(Condensed; full detail in `.synapse/catalog.json`.)

- Extend a `Base*Controller` + `Base*Service` pair; override the **`protected abstract execute*`** method
  (`executeCreate`/`executeRead`/`executeUpdate`/`executeDelete`), **not** the public method.
- `BaseServiceRequest` is an **interface** (`implements`); `BaseServiceResponse` is an **abstract class**
  with a `String id` (`extends`).
- Reads are **POST with a body** at `/inquiry_results` (one) and `/multiple_results` (many);
  only get-by-id is a real `GET /{id}`. `BaseReadPolyService.executeRead` returns `Page<O>`.
- Repositories extend Spring Data `JpaRepository<Entity, Long>` directly — **no** Synapse base repo.
- App config `@Import`s the layer config (`ServiceRestConfig`) which **auto-registers** the
  `ControllerExceptionHandler` — never write your own `@RestControllerAdvice`.
- Errors: `ApplicationClientException(devMsg, ErrorCode, String...)` (4XX) /
  `ApplicationServerException(Throwable)` (5XX).
- **Two imperative families exist** — prefer `service.rest.*` (granular, used by all samples) over
  `api.rest.imperative.*` (unified `BaseService.execute`). Don't mix them. (See RECOMMENDATIONS #B2.)

## Open items

| Item | Status | Where |
|---|---|---|
| CodeQL / Actions billing lock | **Blocked** — needs org admin to clear billing; then re-run checks | PR #481 checks |
| Stale CodeQL workflow (`checkout@v2`, `codeql-action@v1`) | Proposed follow-up PR (`v4`/`v3` + `setup-java@v4`) | `.github/workflows/codeql-analysis.yml` |
| Resolve dual imperative API family | Proposed, needs team decision | RECOMMENDATIONS #B2 |
| Normalize per-DB datasource property keys | Proposed, needs team decision | RECOMMENDATIONS #B3 |
| Generate `catalog.json` from source (anti-drift) | Proposed | RECOMMENDATIONS #B1 |
| `bump-version.yml` should also bump archetype catalog + catalog.json | Proposed | RECOMMENDATIONS #B5 |
| ArchUnit rules → shared `synapse-architecture-test` module | Proposed | RECOMMENDATIONS #B6 |
| Local `./mvnw … compile` to confirm `@implSpec` edits | Pending (sandbox is offline) | `service/synapse-service-rest` |

## Update protocol

Keep this file true. When a change touches any of the following, update the relevant section **in the
same commit** and refresh the `_Last updated_` line:

1. **A base class signature / new operation** → update `.synapse/catalog.json` first, then the
   "Critical facts" section here if a headline fact changed; bump `catalogVersion` if the contract changed.
2. **Framework or catalog version** → update the snapshot line and `_Last updated_`.
3. **Tooling added/moved/removed** → update the "AI-agent tooling map".
4. **An open item resolved or added** (PR merged, CI fixed, proposal accepted) → update "Open items".
5. **Branch/PR status changes** → update "Current snapshot".

Add a one-line entry to the log below for each material update.

### Update log
- 2026-05-30 — Created. Established the AI-agent tooling layer; PR #481 open; CodeQL blocked on billing.
