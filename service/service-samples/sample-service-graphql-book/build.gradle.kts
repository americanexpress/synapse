dependencies {
    implementation(project(":service:synapse-service-graphql")) {
        exclude(group="org.springframework", module="spring-web")
    }
}

description = "sample-service-graphql-book"
