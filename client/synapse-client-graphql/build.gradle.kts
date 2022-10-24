dependencies {
    implementation(project(":client:synapse-client-rest"))
    implementation("org.slf4j:slf4j-ext:1.7.36")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    implementation("com.graphql-java:graphql-java-tools:5.2.4")
}

description = "synapse-client-graphql"
