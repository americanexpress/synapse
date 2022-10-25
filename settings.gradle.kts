pluginManagement {
    repositories {
        maven {
            url = uri("https://ci-repo.aexp.com/java-proxy/content/repositories/iq-gradle/")
        }
    }
}

rootProject.name = "synapse"
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
//include("service:service-samples:sample-service-graphql-book")
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
//        maven {
//            url = uri("https://ci-repo.aexp.com/java-proxy/content/groups/prod/")
//        }

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

            library("junit", "junit", "junit").version("4.13.2")
            library("java-client", "com.couchbase.client", "java-client").version("3.3.4")

            library("commons-codec", "commons-codec", "commons-codec").version("1.15")
            library("commons-io", "commons-io", "commons-io").version("2.11.0")

            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").version("2.13.4")
            library("jackson-dataformat-xml", "com.fasterxml.jackson.dataformat", "jackson-dataformat-xml").version("2.13.4")
            library("jackson-module-jaxb-annotations", "com.fasterxml.jackson.module", "jackson-module-jaxb-annotations").version("2.13.4")

            library("system-rules", "com.github.stefanbirkner", "system-rules").version("1.19.0")

            library("google-http-client", "com.google.http-client", "google-http-client").version("1.42.2")

            library("graphql-java-extended-scalars", "com.graphql-java", "graphql-java-extended-scalars").version("19.0")
            library("graphql-java-tools", "com.graphql-java", "graphql-java-tools").version("5.2.4")
            library("graphiql-spring-boot-starter", "com.graphql-java-kickstart", "graphiql-spring-boot-starter").version("11.1.0")
            library("voyager-spring-boot-starter", "com.graphql-java-kickstart", "voyager-spring-boot-starter").version("11.1.0")

            library("h2", "com.h2database", "h2").version("2.1.214")
            library("jasypt", "org.jasypt", "jasypt").version("1.9.3")
            library("openpojo", "com.openpojo", "openpojo").version("0.9.1")

            library("querydsl-apt", "com.querydsl", "querydsl-apt").version("5.0.0")
            library("querydsl-jpa", "com.querydsl", "querydsl-jpa").version("5.0.0")

            library("reactor-core", "io.projectreactor", "reactor-core").version("3.4.24")
            library("reactor-test", "io.projectreactor", "reactor-test").version("3.4.24")

            library("swagger-annotations", "io.swagger", "swagger-annotations").version("1.6.6")

            library("javax-persistence-api", "javax.persistence", "javax.persistence-api").version("2.2")
            library("validation-api", "javax.validation", "validation-api").version("2.0.1.Final")

            library("commons-collections4", "org.apache.commons", "commons-collections4").version("4.4")
            library("commons-lang3", "org.apache.commons", "commons-lang3").version("3.12.0")

            library("log4j-api", "org.apache.logging.log4j", "log4j-api").version("2.19.0")
            library("log4j-core", "org.apache.logging.log4j", "log4j-core").version("2.19.0")
            library("tomcat-embed-core", "org.apache.tomcat.embed", "tomcat-embed-core").version("9.0.62")

            library("hibernate-ehcache", "org.hibernate", "hibernate-ehcache").version("5.6.12.Final")

            library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").version("5.9.1")
            library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").version("5.9.1")

            library("junit-platform-launcher", "org.junit.platform", "junit-platform-launcher").version("1.9.1")
            library("junit-platform-runner", "org.junit.platform", "junit-platform-runner").version("1.9.1")
            library("junit-platform-suite-api", "org.junit.platform", "junit-platform-suite-api").version("1.9.1")

            library("junit-vintage-engine", "org.junit.vintage", "junit-vintage-engine").version("5.9.1")

            library("mockito-core", "org.mockito", "mockito-core").version("4.8.0")
            library("mockito-junit-jupiter", "org.mockito", "mockito-junit-jupiter").version("4.8.0")

            library("mongodb-driver-sync", "org.mongodb", "mongodb-driver-sync").version("4.7.2")
            library("postgresql", "org.postgresql", "postgresql").version("42.5.0")
            library("lombok", "org.projectlombok", "lombok").version("1.18.24") //should this be removed
            library("slf4j-ext", "org.slf4j", "slf4j-ext").version("1.7.36")

            library("springdoc-openapi-ui", "org.springdoc", "springdoc-openapi-ui").version("1.6.12")
            library("springdoc-openapi-data-rest", "org.springdoc", "springdoc-openapi-data-rest").version("1.6.12")
            library("springdoc-openapi-webmvc-core", "org.springdoc", "springdoc-openapi-webmvc-core").version("1.6.12")
            library("springdoc-openapi-webflux-ui", "org.springdoc", "springdoc-openapi-webflux-ui").version("1.6.12")
            library("springdoc-openapi-native", "org.springdoc", "springdoc-openapi-native").version("1.6.12")

            library("spring-beans", "org.springframework", "spring-beans").version("5.3.23")
            library("spring-context", "org.springframework", "spring-context").version("5.3.23")
            library("spring-context-support", "org.springframework", "spring-context-support").version("5.3.23")
            library("spring-core", "org.springframework", "spring-core").version("5.3.23")
            library("spring-test", "org.springframework", "spring-test").version("5.3.23")
            library("spring-tx", "org.springframework", "spring-tx").version("5.3.23")

            library("spring-boot-autoconfigure", "org.springframework.boot", "spring-boot-autoconfigure").version("2.7.4")
            library("spring-boot-starter", "org.springframework.boot", "spring-boot-starter").version("2.7.4")
            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").version("2.7.4")
            library("spring-boot-starter-data-rest", "org.springframework.boot", "spring-boot-starter-data-rest").version("2.7.4")
            library("spring-boot-starter-data-mongodb-reactive", "org.springframework.boot", "spring-boot-starter-data-mongodb-reactive").version("2.7.4")

            library("spring-boot-starter-log4j2", "org.springframework.boot", "spring-boot-starter-log4j2").version("2.7.4")

            library("spring-boot-starter-actuator", "org.springframework.boot", "spring-boot-starter-actuator").version("2.7.4")
            library("spring-boot-starter-aop", "org.springframework.boot", "spring-boot-starter-aop").version("2.7.4")
            library("spring-boot-starter-json", "org.springframework.boot", "spring-boot-starter-json").version("2.7.4")
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").version("2.7.4")
            library("spring-boot-starter-validation", "org.springframework.boot", "spring-boot-starter-validation").version("2.7.4")
            library("spring-boot-starter-web", "org.springframework.boot", "spring-boot-starter-web").version("2.7.4")
            library("spring-boot-starter-webflux", "org.springframework.boot", "spring-boot-starter-webflux").version("2.7.4")
            library("spring-boot-starter-ws", "org.springframework.boot", "spring-boot-starter-ws").version("1.4.7.RELEASE")
            library("spring-boot-test-autoconfigure", "org.springframework.boot", "spring-boot-test-autoconfigure").version("2.7.4")

            library("spring-data-cassandra", "org.springframework.data", "spring-data-cassandra").version("3.4.3")
            library("spring-data-couchbase", "org.springframework.data", "spring-data-couchbase").version("4.4.3")
            library("spring-data-mongodb", "org.springframework.data", "spring-data-mongodb").version("3.4.5")

            library("spring-kafka", "org.springframework.kafka", "spring-kafka").version("2.8.9")

            library("spring-plugin-core", "org.springframework.plugin", "spring-plugin-core").version("2.0.0.RELEASE")

            library("spring-security-core", "org.springframework.security", "spring-security-core").version("5.7.3")
            library("spring-security-test", "org.springframework.security", "spring-security-test").version("5.7.3")

            library("cassandra", "org.testcontainers", "cassandra").version("1.17.5")
            library("testcontainers-junit-jupiter", "org.testcontainers", "junit-jupiter").version("1.17.5")
            library("testcontainers", "org.testcontainers", "testcontainers").version("1.17.5")

            library("ehcache", "net.sf.ehcache", "ehcache").version("2.10.9.2")

        }
    }

}
