dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    api("com.graphql-java:graphql-java-extended-scalars:19.0")
    api("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.1.0")
    api("com.graphql-java-kickstart:voyager-spring-boot-starter:11.1.0")
}

description = "synapse-service-graphql"
