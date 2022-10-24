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

}
