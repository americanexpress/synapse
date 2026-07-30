# Synapse convention tests (ArchUnit)

`SynapseConventionTest.java` turns Synapse's implicit conventions into **executable, fail-fast checks**.
Its purpose is to give AI agents (and humans) a deterministic verify loop: generate code → `mvn test`
→ read the precise failure → fix. This catches the exact mistakes that are easy to make against
Synapse's two-families / `execute*` / interface-vs-abstract design.

## What it enforces

- `@RestController`s extend a Synapse `Base*Controller`.
- Controllers live in a `..controller..` package and **do not** declare their own `@PostMapping`/etc.
  (the base owns the verb).
- Synapse services live in a `..service..` package.
- No hand-written `@RestControllerAdvice` (the layer config auto-registers `ControllerExceptionHandler`).
- `@Entity` classes extend Synapse `BaseEntity`.

## Use in an application module

1. Add the dependency (test scope):
   ```xml
   <dependency>
     <groupId>com.tngtech.archunit</groupId>
     <artifactId>archunit-junit5</artifactId>
     <version>1.3.0</version>
     <scope>test</scope>
   </dependency>
   ```
2. Copy `SynapseConventionTest.java` into the module's `src/test/java`.
3. Set `APP_PACKAGE` to your application's root package.
4. `mvn test`.

## Status

⚠️ **Build-unverified.** This is a ready-to-use template under `docs/agent`; it is intentionally not
yet part of the Maven reactor, so it has not been compiled/run in CI here. The recommended permanent
home is a shared, freezable rule set in a new `synapse-architecture-test` module that application
modules depend on — see `docs/agent/RECOMMENDATIONS.md` (#4). Class/method names referenced by the
rules were taken from the verified base classes (`BaseController`, `BaseService`, `BaseEntity`); if the
framework renames them, update the constants at the top of the test.
