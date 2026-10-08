---
name: synapse-engineer
description: >
  Scaffold and implement Spring Boot code with the American Express Synapse framework (io.americanexpress.synapse) —
  REST/reactive services, REST/SOAP/GraphQL clients, and JPA/Mongo data access. Use when creating a new Synapse app
  or adding a Synapse controller/service/repository to an existing one. Provides the base-class decision table,
  copy-ready file skeletons, the Maven archetype command, and pom/properties templates.
---

# Synapse Engineer — scaffolding procedure

Implement code the Synapse way: extend one Base class per layer and override its single `protected abstract
execute*` method. All signatures are verified against this repo (`io.americanexpress.synapse`, Java 21, Spring
Boot 3.5.x, `jakarta.*`, `XLogger`). Canonical worked example:
`service/service-samples/sample-service-rest-mysql-book` (service) + `data/data-samples/sample-data-mysql-book` (data).

## Ground truth: `.synapse/catalog.json`

Before scaffolding, read **`.synapse/catalog.json`** — the machine-readable catalog of every base class,
its generics, the exact `execute*` method to override, HTTP verb/path, the config triple, ErrorCodes, and
archetype coordinates. It is authoritative; prefer it over re-deriving signatures from source. The tables
below mirror it for quick reference. (An MCP server at `.synapse/mcp/` exposes the same data as
`list_base_classes` / `scaffold_operation` / `validate_module` for non–Claude-Code agents.)

## Step 1 — Choose the path

- **New standalone app** → use the Maven archetype (Step 2A).
- **Add to an existing module** → hand-write the slice (Step 2B onward).

## Step 2A — Generate from an archetype

Archetypes (under `archetype/`, coords `io.americanexpress.synapse:synapse-archetype-<kind>`): service-rest-`get`/`post`/`put`/`delete`,
their `service-rest-reactive-*` variants, `client-rest`(+`-get/-post/-put/-delete` and reactive), `client-graphql`,
`data-postgres`. Full list in `archetype/archetype-catalog.xml`.

```bash
mvn archetype:generate \
  -DarchetypeGroupId=io.americanexpress.synapse \
  -DarchetypeArtifactId=synapse-archetype-service-rest-post \
  -DarchetypeVersion=<released framework version, e.g. 0.4.27> \
  -DgroupId=com.example.myapp -DartifactId=my-service -Dversion=0.0.1-SNAPSHOT \
  -DjavaVersion=21 \
  -Dauthor="Your Name" -DapiName="MyApi" -DbaseUrl="/v1/books" -DclassName="Book" \
  -DinteractiveMode=false
```

Required/typical properties: `groupId`, `artifactId`, `version`, `javaVersion` (default 21), plus archetype-specific
`author`, `apiName`, `className`, and `baseUrl` (service) / `url` (client). Then fill in domain fields + business logic (Steps 3–5).

> Editing archetype *templates*? CI enforces tokenization in `archetype-resources/`: keep
> `<synapse.version>@project.version@</synapse.version>`, `maven-compiler-plugin` with
> `<version>@maven.compiler.plugin.version@</version>`, and `requiredProperty key="javaVersion"` defaulting to
> `@maven.compiler.source@`. Never hardcode versions there.

## Step 2B — Pick the base class (imperative REST)

| Operation | Controller extends | Service extends | Override (`protected abstract`) |
|---|---|---|---|
| Create (POST → 201) | `BaseCreateController<Req,Resp,Svc>` | `BaseCreateService<Req,Resp>` | `Resp executeCreate(HttpHeaders, Req)` |
| Read one by body (POST `/inquiry_results`) | `BaseReadMonoController<Req,Resp,Svc>` | `BaseReadMonoService<Req,Resp>` | `Resp executeRead(HttpHeaders, Req)` |
| Read many (POST `/multiple_results`) | `BaseReadPolyController<Req,Resp,Svc>` | `BaseReadPolyService<Req,Resp>` | `Page<Resp> executeRead(HttpHeaders, Req)` |
| Get one by id (GET `/{id}`) | `BaseGetMonoController<Resp,Svc>` | `BaseGetMonoService<Resp>` | `Resp executeRead(HttpHeaders, String identifier)` |
| Update (PUT → 204) | `BaseUpdateController<Req,Svc>` | `BaseUpdateService<Req>` | `void executeUpdate(HttpHeaders, Req)` |
| Delete (DELETE `/{identifier}` → 204) | `BaseDeleteController<Svc>` | `BaseDeleteService` | `void executeDelete(HttpHeaders, String id)` |

Packages: controllers `io.americanexpress.synapse.service.rest.controller`, services `…service.rest.service`,
models `…service.rest.model`. Note: reads are POST-with-body except get-by-id; you override `execute*`, not the public method.

**Reactive** (`io.americanexpress.synapse.service.reactive.rest.*`): same shapes but controllers return
`Mono<ResponseEntity<O>>`/`Flux<O>` and services override `Mono<O>`/`Flux<O>`/`Mono<Void>`; app config **extends**
`BaseServiceReactiveRestConfig`. Choose one paradigm; never mix.

## Step 3 — Write the slice (verified patterns)

App package root e.g. `com.example.myapp` with subpackages `config/ controller/ service/ model/ entity/ dao/`.

**Request** — `implements BaseServiceRequest` (it is an interface) + jakarta validation:
```java
public class CreateBookRequest implements BaseServiceRequest {  // io.americanexpress.synapse.service.rest.model.BaseServiceRequest
    @NotBlank private String title;       // jakarta.validation.constraints.NotBlank
    @NotBlank private String author;
    private String createdBy;
    // getters / setters
}
```

**Response** — `extends BaseServiceResponse` (abstract; inherits `String id` + getId/setId):
```java
public class CreateBookResponse extends BaseServiceResponse {    // io.americanexpress.synapse.service.rest.model.BaseServiceResponse
    private String title; private String author; // getters/setters; id via inherited setId(...)
}
```

**Controller** — declaration only (base maps the verb):
```java
@RestController
@RequestMapping("/v1/books")
public class CreateBookController
        extends BaseCreateController<CreateBookRequest, CreateBookResponse, CreateBookService> { }
```

**Service** — override the `execute*` method; constructor-inject the repository:
```java
@Service
public class CreateBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {
    private final BookRepository bookRepository;
    public CreateBookService(BookRepository bookRepository) { this.bookRepository = bookRepository; }

    @Override
    protected CreateBookResponse executeCreate(HttpHeaders headers, CreateBookRequest request) {
        if (bookRepository.findByTitle(request.getTitle()) != null) {
            throw new ApplicationClientException(ErrorCode.RESOURCE_OUT_OF_SYNC.getMessage(),
                    ErrorCode.RESOURCE_OUT_OF_SYNC, (String[]) null);
        }
        BookEntity entity = new BookEntity();
        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        BookEntity saved = bookRepository.save(entity);
        CreateBookResponse response = new CreateBookResponse();
        response.setId(String.valueOf(saved.getId()));
        response.setTitle(saved.getTitle());
        response.setAuthor(saved.getAuthor());
        return response;
    }
}
```
Imports: `io.americanexpress.synapse.service.rest.service.BaseCreateService`,
`io.americanexpress.synapse.framework.exception.ApplicationClientException`,
`io.americanexpress.synapse.framework.exception.model.ErrorCode`, `org.springframework.http.HttpHeaders`.

**Entity + Repository** (only if persisted):
```java
@Entity @Table(name = "book")
public class BookEntity extends BaseEntity {                 // io.americanexpress.synapse.data.jpa.entity.BaseEntity (id + audit cols free)
    @Column(name = "title") private String title;
    @Column(name = "author") private String author;  // getters/setters
}

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {   // Spring Data directly
    BookEntity findByTitle(String title);
    BookEntity findByTitleAndAuthor(String title, String author);
}
```

## Step 4 — Wire it

**App / service config** — import the layer config + your data config:
```java
@Configuration
@PropertySource("classpath:service-book-application.properties")
@ComponentScan(basePackages = "com.example.myapp")
@Import({ BookDataConfig.class, ServiceRestConfig.class })   // io.americanexpress.synapse.service.rest.config.ServiceRestConfig
public class BookConfig { }
```
`ServiceRestConfig` auto-registers the `ControllerExceptionHandler` — don't write one.

**Data config** — extend the per-DB base, point it at your entity + dao packages:
```java
@Configuration
@PropertySource("classpath:data-book-application.properties")
@EnableJpaRepositories(basePackages = "com.example.myapp.dao")
public class BookDataConfig extends BasePostgresDataConfig {   // or BaseMySqlDataConfig, etc.
    public BookDataConfig(Environment environment) { super(environment); }
    @Override
    protected void setPackagesToScan(LocalContainerEntityManagerFactoryBean emf) {
        emf.setPackagesToScan("com.example.myapp.entity");
    }
}
// Enable auditing once: @Import(InstantJpaAuditingDataConfig.class) or add @EnableJpaAuditing(dateTimeProviderRef="instantDateTimeProvider").
```

**Main class**: a normal `@SpringBootApplication` (samples log readiness via `XLoggerFactory.getXLogger(...)`).

**pom.xml** — Spring Boot parent + the synapse modules + driver:
```xml
<dependencies>
  <dependency><groupId>io.americanexpress.synapse</groupId>
    <artifactId>synapse-service-rest</artifactId><version>${synapse.version}</version></dependency>
  <dependency><groupId>io.americanexpress.synapse</groupId>
    <artifactId>synapse-data-postgres</artifactId><version>${synapse.version}</version></dependency>
  <dependency><groupId>io.americanexpress.synapse</groupId>
    <artifactId>synapse-framework-exception</artifactId><version>${synapse.version}</version></dependency>
  <dependency><groupId>org.postgresql</groupId><artifactId>postgresql</artifactId><scope>runtime</scope></dependency>
  <!-- add synapse-framework-api-docs for Swagger UI; synapse-framework-test / synapse-service-test for test bases -->
</dependencies>
```
Swap data module + driver per DB (mysql → `synapse-data-mysql` + `com.mysql:mysql-connector-j`, etc.).

**application.properties** (Postgres example):
```properties
spring.application.name=Book Rest Service
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/book
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.default_schema=synapse
spring.jpa.hibernate.ddl-auto=update
```
(The MySQL sample uses `spring.mysql.datasource.*` keys read by `BaseMySqlDataConfig`; match whichever base config you extend.)

## Step 4.5 — Scaffold a test (don't skip)

`synapse-service-test` provides base test classes — always emit at least one so the module ships green.
Add it as a test-scope dependency and extend the matching base:

- `io.americanexpress.synapse.service.test.controller.BaseControllerTest` — controller slice test base.
- `io.americanexpress.synapse.service.test.controller.BaseControllerIT` — integration test base.
- Per-operation unit-test bases: `BaseCreateMonoControllerUnitTest`, `BaseReadMonoControllerUnitTest`,
  `BaseReadPolyControllerUnitTest`, `BaseGetMonoControllerUnitTest`, `BaseUpdateControllerUnitTest`,
  `BaseDeleteControllerUnitTest`.

For convention enforcement (controllers extend a base, services override `execute*`, requests
`implements`/responses `extends`, no custom advice), copy `docs/agent/archunit/SynapseConventionTest.java`
into `src/test/java`, set `APP_PACKAGE`, add `com.tngtech.archunit:archunit-junit5` (test scope), and run
`mvn test` as the verify loop.

## Step 5 — Conventions & verification

- **Override `execute*`, not the public method.** `BaseServiceRequest` is an interface (`implements`);
  `BaseServiceResponse` is abstract (`extends`, gives `id`).
- **Errors**: `throw new ApplicationClientException(devMsg, ErrorCode, msgArgs...)` (4XX) or
  `new ApplicationServerException(cause)` (5XX). Real `ErrorCode`s: `GENERIC_4XX_ERROR`, `MISSING_HTTP_HEADER_ERROR`,
  `UNAUTHORIZED`, `FORBIDDEN`, `NOT_FOUND`, `GENERIC_5XX_ERROR`, `AUTHENTICATION_ERROR`, `TOO_MANY_REQUESTS`,
  `LOCKED`, `RESOURCE_OUT_OF_SYNC`. Don't build error bodies by hand.
- **Validation**: jakarta constraints on the Request; the base controller's `@Valid` enforces them.
- **Don't** add `@PostMapping`/`ResponseEntity` plumbing to controllers — the base owns it. One controller class
  per operation, grouped under a shared `@RequestMapping` base path.
- **Build/test**: `./mvnw -pl <module-path> -am clean test` (root reactor: `./mvnw clean package`).
- When in doubt, read the nearest `*-samples` module and mirror it.

## Step 6 — Update `CONTEXT.md` (do not skip)

`/CONTEXT.md` is the repo's living context for AI agents; keeping it current is part of every task.
Before you finish, update it in the **same change** if your work touched anything it records:

- Base-class signature / new operation → update `.synapse/catalog.json`, then `CONTEXT.md` "Critical facts".
- Framework or catalog version bump → snapshot line + `_Last updated_` stamp.
- Tooling added/moved/removed → "AI-agent tooling map".
- Tracked item resolved/opened (PR merged, CI fixed) → "Open items".
- Branch/PR status change → "Current snapshot".

Always refresh `_Last updated_` and append a one-line entry to the `CONTEXT.md` "Update log". The full
rules live in `CONTEXT.md` → **Update protocol**. A stale `CONTEXT.md` is a defect; fix it now, not later.
