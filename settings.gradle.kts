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

            library("java-client", "com.couchbase.client", "java-client").version("3.3.4")
            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").version("2.13.4")
            library("graphql-java-tools", "com.graphql-java", "graphql-java-tools").version("5.2.4")
            library("h2", "com.h2database", "h2").version("2.1.214")

            library("reactor-core", "io.projectreactor", "reactor-core").version("3.4.24")
            library("javax-persistence-api", "javax.persistence", "javax.persistence-api").version("2.2")

            library("commons-lang3", "org.apache.commons", "commons-lang3").version("3.12.0")
            library("hibernate-ehcache", "org.hibernate", "hibernate-ehcache").version("5.6.12.Final")

            library("mongodb-driver-sync", "org.mongodb", "mongodb-driver-sync").version("4.7.2")
            library("postgresql", "org.postgresql", "postgresql").version("42.5.0")
            library("lombok", "org.projectlombok", "lombok").version("1.18.24") //should this be removed
            library("slf4j-ext", "org.slf4j", "slf4j-ext").version("1.7.36")

            library("spring-context-support", "org.springframework", "spring-context-support").version("5.3.23")
            library("spring-core", "org.springframework", "spring-core").version("5.3.23")
            library("spring-tx", "org.springframework", "spring-tx").version("5.3.23")

            library("spring-boot-starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").version("2.7.4")
            library("spring-boot-starter-data-rest", "org.springframework.boot", "spring-boot-starter-data-rest").version("2.7.4")
            library("spring-boot-starter-data-mongodb-reactive", "org.springframework.boot", "spring-boot-starter-data-mongodb-reactive").version("2.7.4")
            library("spring-boot-starter-test", "org.springframework.boot", "spring-boot-starter-test").version("2.7.4")
            library("spring-boot-starter-webflux", "org.springframework.boot", "spring-boot-starter-webflux").version("2.7.4")
            library("spring-boot-starter-ws", "org.springframework.boot", "spring-boot-starter-ws").version("1.4.7.RELEASE")
            library("spring-boot-test-autoconfigure", "org.springframework.boot", "spring-boot-test-autoconfigure").version("2.7.4")

            library("spring-data-cassandra", "org.springframework.data", "spring-data-cassandra").version("3.4.3")
            library("spring-data-couchbase", "org.springframework.data", "spring-data-couchbase").version("4.4.3")
            library("spring-data-mongodb", "org.springframework.data", "spring-data-mongodb").version("3.4.5")

            library("ehcache", "net.sf.ehcache", "ehcache").version("2.10.9.2")

        }
    }

}
