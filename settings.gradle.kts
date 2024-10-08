pluginManagement {
    repositories {
        mavenCentral()
//        maven {
//            url = uri("https://ci-repo.aexp.com/java-proxy/content/repositories/iq-gradle/")
//        }
    }
}

rootProject.name = "synapse"
include("archetype")
include("archetype:synapse-archetype-client-graphql")
include("archetype:synapse-archetype-client-rest")
include("archetype:synapse-archetype-client-rest-delete")
include("archetype:synapse-archetype-client-rest-get")
include("archetype:synapse-archetype-client-rest-post")
include("archetype:synapse-archetype-client-rest-put")
include("archetype:synapse-archetype-client-rest-reactive")
include("archetype:synapse-archetype-client-rest-reactive-delete")
include("archetype:synapse-archetype-client-rest-reactive-get")
include("archetype:synapse-archetype-client-rest-reactive-post")
include("archetype:synapse-archetype-client-rest-reactive-put")
include("archetype:synapse-archetype-data-postgres")
include("archetype:synapse-archetype-service-rest-delete")
include("archetype:synapse-archetype-service-rest-get")
include("archetype:synapse-archetype-service-rest-post")
include("archetype:synapse-archetype-service-rest-put")
include("archetype:synapse-archetype-service-rest-reactive-post")
include("client")
include("client:synapse-client-graphql")
include("client:synapse-client-rest")
include("client:synapse-client-soap")
include("client:synapse-client-test")
include("data")
include("data:data-samples")
include("data:data-samples:sample-data-cassandra-book")
include("data:data-samples:sample-data-cassandra-reactive")
include("data:data-samples:sample-data-mongodb-book")
include("data:data-samples:sample-data-mongodb-reactive")
include("data:data-samples:sample-data-postgres-book")
include("data:synapse-data-cassandra")
include("data:synapse-data-couchbase")
include("data:synapse-data-mongodb")
include("data:synapse-data-postgres")
include("framework")
include("framework:synapse-framework-api-docs")
include("framework:synapse-framework-exception")
include("framework:synapse-framework-logging")
include("framework:synapse-framework-test")
include("function")
include("function:function-samples")
include("function:function-samples:sample-function-greeter-aws")
include("function:function-samples:sample-function-greeter-gcp")
include("function:function-samples:sample-function-greeting")
include("function:function-samples:sample-function-rest-book")
include("function:synapse-function")
include("publisher")
include("publisher:synapse-publisher-kafka")
include("service")
include("service:service-samples")
include("service:service-samples:sample-service-graphql-book")
include("service:service-samples:sample-service-reactive-cassandra-book")
include("service:service-samples:sample-service-rest-book")
include("service:service-samples:sample-service-rest-cassandra-book")
include("service:synapse-service-graphql")
include("service:synapse-service-rest")
include("service:synapse-service-test")
include("utility")
include("utility:synapse-utilities-common")

dependencyResolutionManagement {
    // FAIL_ON_PROJECT_REPOS -> If this mode is set, any repository declared directly in a project,
    // either directly or via a plugin, will trigger a build error.
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    // Define repositories for all projects
    repositories {
        mavenLocal()
        mavenCentral()

        maven {
            url = uri ("https://oss.jfrog.org/artifactory/oss-snapshot-local")
        }

        maven {
            url = uri("https://repo.spring.io/release")
        }

        maven {
            url = uri("https://repo.spring.io/snapshot")
        }

        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
    }

    versionCatalogs {
        create("libs") {
            version("graphql", "11.1.0")
            version("jackson", "2.13.4")
            version("junit", "5.9.1")
            version("junit-platform", "1.9.1")
            version("log4j", "2.19.0")
            version("mockito", "4.8.0")
            version("querydsl", "5.0.0")
            version("reactor", "3.4.24")
            version("spring", "5.3.23")
            version("springdoc", "1.6.12")
            version("spring-boot", "2.7.4")
            version("spring-security", "5.7.3")
            version("testcontainers", "1.17.5")

            library("java-client", "com.couchbase.client", "java-client").version("3.3.4")

            library("commons-codec", "commons-codec", "commons-codec").version("1.15")
            library("commons-io", "commons-io", "commons-io").version("2.11.0")

            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").versionRef("jackson")
            library("jackson-dataformat-xml", "com.fasterxml.jackson.dataformat", "jackson-dataformat-xml").versionRef("jackson")
            library("jackson-module-jaxb-annotations", "com.fasterxml.jackson.module", "jackson-module-jaxb-annotations").versionRef("jackson")

            library("system-rules", "com.github.stefanbirkner", "system-rules").version("1.19.0")

            library("google-http-client", "com.google.http-client", "google-http-client").version("1.42.2")

            library("graphql-java-extended-scalars", "com.graphql-java", "graphql-java-extended-scalars").version("19.0")
            library("graphql-java-tools", "com.graphql-java", "graphql-java-tools").version("5.2.4")
            library("graphiql-spring-boot-starter", "com.graphql-java-kickstart", "graphiql-spring-boot-starter").versionRef("graphql")
            library("graphql-spring-boot-starter", "com.graphql-java-kickstart", "graphql-spring-boot-starter").versionRef("graphql")
            library("voyager-spring-boot-starter", "com.graphql-java-kickstart", "voyager-spring-boot-starter").versionRef("graphql")

            library("h2", "com.h2database", "h2").version("2.1.214")
            library("openpojo", "com.openpojo", "openpojo").version("0.9.1")

            library("querydsl-apt", "com.querydsl", "querydsl-apt").versionRef("querydsl")
            library("querydsl-jpa", "com.querydsl", "querydsl-jpa").versionRef("querydsl")

            library("graphql-spqr-spring-boot-starter", "io.leangen.graphql", "graphql-spqr-spring-boot-starter").version("0.0.6")

            library("reactor-core", "io.projectreactor", "reactor-core").versionRef("reactor")
            library("reactor-test", "io.projectreactor", "reactor-test").versionRef("reactor")

            library("swagger-annotations", "io.swagger", "swagger-annotations").version("1.6.6")

            library("javax-persistence-api", "javax.persistence", "javax.persistence-api").version("2.2")
            library("validation-api", "javax.validation", "validation-api").version("2.0.1.Final")
            library("junit", "junit", "junit").version("4.13.2")

            library("commons-collections4", "org.apache.commons", "commons-collections4").version("4.4")
            library("commons-lang3", "org.apache.commons", "commons-lang3").version("3.12.0")

            library("log4j-api", "org.apache.logging.log4j", "log4j-api").versionRef("log4j")
            library("log4j-core", "org.apache.logging.log4j", "log4j-core").versionRef("log4j")
            
            library("tomcat-embed-core", "org.apache.tomcat.embed", "tomcat-embed-core").version("9.0.62")

            library("hibernate-ehcache", "org.hibernate", "hibernate-ehcache").version("5.6.12.Final")
            
            library("jasypt", "org.jasypt", "jasypt").version("1.9.3")

            library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")
            library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")

            library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").versionRef("junit-platform")
            library("junit-platform-runner", "org.junit.platform", "junit-platform-runner").versionRef("junit-platform")
            library("junit-platform-suite-api", "org.junit.platform", "junit-platform-suite-api").versionRef("junit-platform")

            library("junit-vintage-engine", "org.junit.vintage", "junit-vintage-engine").versionRef("junit")

            library("mockito-core", "org.mockito", "mockito-core").versionRef("mockito")
            library("mockito-junit-jupiter", "org.mockito", "mockito-junit-jupiter").versionRef("mockito")

            library("mongodb-driver-sync", "org.mongodb", "mongodb-driver-sync").version("4.7.2")
            library("postgresql", "org.postgresql", "postgresql").version("42.5.0")
            library("lombok", "org.projectlombok", "lombok").version("1.18.24") //should this be removed
            library("slf4j-ext", "org.slf4j", "slf4j-ext").version("1.7.36")

            library("springdoc-openapi-ui", "org.springdoc", "springdoc-openapi-ui").versionRef("springdoc")
            library("springdoc-openapi-data-rest", "org.springdoc", "springdoc-openapi-data-rest").versionRef("springdoc")
            library("springdoc-openapi-webmvc-core", "org.springdoc", "springdoc-openapi-webmvc-core").versionRef("springdoc")
            library("springdoc-openapi-webflux-ui", "org.springdoc", "springdoc-openapi-webflux-ui").versionRef("springdoc")
            library("springdoc-openapi-native", "org.springdoc", "springdoc-openapi-native").versionRef("springdoc")

            library("spring-beans", "org.springframework", "spring-beans").versionRef("spring")
            library("spring-context", "org.springframework", "spring-context").versionRef("spring")
            library("spring-context-support", "org.springframework", "spring-context-support").versionRef("spring")
            library("spring-core", "org.springframework", "spring-core").versionRef("spring")
            library("spring-test", "org.springframework", "spring-test").versionRef("spring")
            library("spring-tx", "org.springframework", "spring-tx").versionRef("spring")

            library("spring-boot-autoconfigure", "org.springframework.boot", "spring-boot-autoconfigure").versionRef("spring-boot")
            library("spring-boot-starter", "org.springframework.boot", "spring-boot-starter").versionRef("spring-boot")
            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").versionRef("spring-boot")
            library("spring-boot-starter-data-rest", "org.springframework.boot", "spring-boot-starter-data-rest").versionRef("spring-boot")
            library("spring-boot-starter-data-mongodb-reactive", "org.springframework.boot", "spring-boot-starter-data-mongodb-reactive").versionRef("spring-boot")

            library("spring-boot-starter-log4j2", "org.springframework.boot", "spring-boot-starter-log4j2").versionRef("spring-boot")

            library("spring-boot-starter-actuator", "org.springframework.boot", "spring-boot-starter-actuator").versionRef("spring-boot")
            library("spring-boot-starter-aop", "org.springframework.boot", "spring-boot-starter-aop").versionRef("spring-boot")
            library("spring-boot-starter-json", "org.springframework.boot", "spring-boot-starter-json").versionRef("spring-boot")
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
            library("spring-boot-starter-validation", "org.springframework.boot", "spring-boot-starter-validation").versionRef("spring-boot")
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").versionRef("spring-boot")
            library("spring-boot-starter-webflux", "org.springframework.boot", "spring-boot-starter-webflux").versionRef("spring-boot")
            library("spring-boot-starter-ws", "org.springframework.boot", "spring-boot-starter-ws").version("1.4.7.RELEASE")
            library("spring-boot-test-autoconfigure", "org.springframework.boot", "spring-boot-test-autoconfigure").versionRef("spring-boot")

            library("spring-data-cassandra", "org.springframework.data", "spring-data-cassandra").version("3.4.3")
            library("spring-data-couchbase", "org.springframework.data", "spring-data-couchbase").version("4.4.3")
            library("spring-data-mongodb", "org.springframework.data", "spring-data-mongodb").version("3.4.5")

            library("spring-kafka", "org.springframework.kafka", "spring-kafka").version("2.8.9")

            library("spring-plugin-core", "org.springframework.plugin", "spring-plugin-core").version("2.0.0.RELEASE")

            library("spring-security-core", "org.springframework.security", "spring-security-core").versionRef("spring-security")
            library("spring-security-test", "org.springframework.security", "spring-security-test").versionRef("spring-security")

            library("cassandra", "org.testcontainers", "cassandra").versionRef("testcontainers")
            library("testcontainers-junit-jupiter", "org.testcontainers", "junit-jupiter").versionRef("testcontainers")
            library("testcontainers", "org.testcontainers", "testcontainers").versionRef("testcontainers")

            library("ehcache", "net.sf.ehcache", "ehcache").version("2.10.9.2")

        }
    }

}
