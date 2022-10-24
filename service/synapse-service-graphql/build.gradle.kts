dependencies {
    implementation(project(":framework:synapse-framework-logging"))
    implementation(project(":framework:synapse-framework-exception"))
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    implementation("com.graphql-java:graphql-java-extended-scalars:19.0")
    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:11.1.0")
    implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.1.0")
    implementation("com.graphql-java-kickstart:voyager-spring-boot-starter:11.1.0")
}

description = "synapse-service-graphql"
