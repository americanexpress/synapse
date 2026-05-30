# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What Synapse is

Synapse (`io.americanexpress.synapse`, Apache 2.0, by American Express) is a **multi-module Maven
library** — a lightweight foundational framework layered on top of Spring Boot. It is *not* an
application; it ships reusable abstract base classes and configuration that downstream teams depend
on to build enterprise services, clients, and data access modules with minimal boilerplate. Artifacts
are published to Maven Central.

The framework enforces a layered, convention-over-configuration architecture: application developers
**extend a Base class for each architectural layer** rather than wiring things from scratch. The Base
classes already encode the HTTP, service, and DAO layer responsibilities (controller exception
handling, metrics interception, pagination, logging, error handling, connection pooling, etc.).

- Java **21**, Spring Boot **3.5.x**, Spring Cloud 2025.x, JUnit 5, Mockito 5.
- Current version: 0.4.x (`-SNAPSHOT` on `develop`).

## Build, test, run

Use the Maven wrapper from the repo root. The root `pom.xml` (packaging `pom`) is the reactor for all modules.

```bash
./mvnw clean package          # compile + run all unit tests across every module (what CI's build job runs)
./mvnw verify                 # full verification incl. integration tests (run after package in CI)
./mvnw clean test             # unit tests only

# Work on a single module (and the modules it depends on):
./mvnw -pl service/synapse-service-rest -am clean test

# A single test class / method:
./mvnw -pl data/synapse-data-postgres test -Dtest=SomeClassTest
./mvnw -pl data/synapse-data-postgres test -Dtest=SomeClassTest#someMethod
```

There is no single runnable app. To see the framework in action, run the reference apps under the
`*-samples` directories (e.g. `service/service-samples`, `client/client-samples`, `data/data-samples`,
`function/function-samples`), which are normal Spring Boot apps (`./mvnw -pl <sample-module> spring-boot:run`).

## Module layout

The reactor groups modules into top-level directories by architectural role. Within each, modules are
named `synapse-<role>-<technology>`:

| Dir | Role | Key modules |
|---|---|---|
| `api` | **Foundational base classes** shared by service & client layers — split `synapse-api-rest-imperative` (Spring Web MVC) and `synapse-api-rest-reactive` (Spring WebFlux). The two mirror each other so switching paradigms is a small change. | imperative, reactive |
| `service` | Business layer — exposes APIs that start a workflow (REST/reactive/GraphQL). | rest, reactive-rest, imperative, reactive, graphql, test |
| `client` | Data-access layer — consumes external APIs. | rest, soap, graphql, test |
| `data` | Data-access layer — CRUD against datastores; each provides config + `BaseEntity` + connection pooling. | jdbc, jpa, postgres, mysql, mssql, oracle, db2, mongodb, cassandra, couchbase, redis |
| `framework` | Cross-cutting concerns. | exception, logging, api-docs, test |
| `publisher` / `subscriber` | Async pub/sub messaging. | kafka |
| `function` | Serverless/function support. | synapse-function |
| `utility` | Small standalone helpers. | utilities-common, cryptography, date, number, telephone |
| `archetype` | **Maven archetypes** that scaffold new apps from the base classes (see CI rule below). | service/client REST get/post/put/delete, reactive variants, data-postgres |

Each role directory also has a `*-samples` module with working reference implementations — the best
place to see how the base classes are meant to be extended.

## Architecture conventions

- **Extend, don't reinvent.** A feature is normally implemented by subclassing the appropriate Base
  class for its layer. CRUD is split into granular base classes — e.g. `BaseCreateController` /
  `BaseReadController` / `BaseReadPolyController` (collections) / `BaseReadMonoController` /
  `BaseUpdateController` / `BaseDeleteController`, and the matching `Base*Service` classes. Pick the
  narrowest base that fits the operation rather than a catch-all.
- **Imperative vs reactive are parallel hierarchies.** When changing a base class in one paradigm,
  check whether the mirror class in the other (`api-rest-imperative` ↔ `api-rest-reactive`,
  `service-rest` ↔ `service-reactive-rest`) needs the same change to keep them aligned.
- Standard request/response models extend `BaseServiceRequest` / `BaseServiceResponse`; config
  classes extend the layer's base config (e.g. `BaseServiceRestConfig`); data entities extend
  `BaseEntity` (auditing fields managed by Spring Data). Errors flow through the two-exception model in
  `framework/synapse-framework-exception` (`ApplicationServerException`, `ApplicationClientException`)
  with an extensible `ErrorCode` enum.
- Modules consistently use the package layout `controller/ service/ model/ config/` (plus
  `repository/`, `dto/` where relevant). Match the sibling module's structure when adding code.

## CI / release

- **CircleCI** (`.circleci/config.yml`) is the primary pipeline on `cimg/openjdk:21.0`: `./mvnw clean
  package` then `./mvnw verify`; separate snapshot/release jobs deploy to Maven Central (Sonatype OSS)
  with GPG signing.
- **Archetype validation runs in CI and will fail the build**: every archetype's
  `archetype-resources/pom.xml` must keep tokenized values — `<synapse.version>@project.version@</synapse.version>`,
  the `maven-compiler-plugin` with `<version>@maven.compiler.plugin.version@</version>`, and each
  `archetype-metadata.xml` must declare a required `javaVersion` property defaulting to
  `@maven.compiler.source@`. Never hardcode versions in archetype templates.
- GitHub Actions: `bump-version.yml` (manual `workflow_dispatch`, bumps via `versions:set`),
  `codeql-analysis.yml`, `compare-dependencies.yml`; Dependabot is active (much of the commit history
  is dependency patching).
- Default branch is `develop`. Releases are cut via `Release/vX.Y.Z` PRs. PRs require approval from
  `@americanexpress/synapse-team` (CODEOWNERS covers all paths).

## AI agent tooling

This repo ships a layer that lets AI coding agents use Synapse correctly without re-deriving signatures
from source. When implementing code *with* Synapse, start here:

- **`.synapse/catalog.json`** — authoritative machine-readable catalog of every base class: generics,
  the exact `protected abstract execute*` method to override, HTTP verb/path, the controller↔service↔config
  triple, `ErrorCode`s, and archetype coordinates (schema in `.synapse/catalog.schema.json`). Prefer it
  over reading the Java. Key facts it encodes that surprise people: `BaseServiceRequest` is an **interface**
  (`implements`); `BaseServiceResponse` is **abstract** with `id` (`extends`); you override `executeCreate`/
  `executeRead`/… not the public method; reads are POST-with-body (`/inquiry_results`, `/multiple_results`)
  except get-by-id; repositories extend Spring Data `JpaRepository` directly (no Synapse base repo).
- **`.synapse/mcp/`** + **`.mcp.json`** — a zero-dependency MCP server exposing the same catalog to any
  MCP client as `list_base_classes` / `scaffold_operation` / `validate_module`. Self-test:
  `python3 .synapse/mcp/synapse_mcp.py --selftest`.
- **`.synapse/agents/synapse-engineer.md`** + **`.synapse/skills/synapse-engineer/`** — a provider-agnostic
  agent persona and scaffolding skill (plain Markdown, no Claude-specific format) with the decision tables,
  copy-ready skeletons, test scaffolding, and the `mvn archetype:generate` recipe. Any AI runtime can load
  them. Claude Code users symlink them in once: `ln -s ../../.synapse/agents .claude/agents` and
  `ln -s ../../.synapse/skills .claude/skills` (`.claude/` is gitignored as provider-specific local state).
- **`docs/agent/archunit/`** — ArchUnit convention tests (template) giving agents a deterministic verify loop.
- **`docs/agent/RECOMMENDATIONS.md`** — proposed framework-level changes for `@americanexpress/synapse-team`
  (resolve the dual imperative API family, normalize per-DB property keys, generate the catalog from source,
  etc.) that should not be made unilaterally because they touch the published API.

Note there are **two** imperative families — `service.rest.*` (granular, used by all samples) and
`api.rest.imperative.*` (unified `BaseService.execute`). Default to `service.rest.*`; don't mix them. See
RECOMMENDATIONS.md #B2.
