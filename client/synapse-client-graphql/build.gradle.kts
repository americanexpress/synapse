dependencies {
    implementation(project(":client:synapse-client-rest"))

    implementation(libs.slf4j.ext)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.graphql.java.tools)
}

description = "synapse-client-graphql"
