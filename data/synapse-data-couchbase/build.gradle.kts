dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    implementation(project(":framework:synapse-framework-logging"))
    implementation("com.couchbase.client:java-client:3.3.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("org.projectlombok:lombok:1.18.24")
    implementation("org.slf4j:slf4j-ext:1.7.36")
    implementation("org.springframework:spring-core:5.3.23")
    implementation("org.springframework.data:spring-data-couchbase:4.4.3")
    implementation("javax.persistence:javax.persistence-api:2.2")
}

description = "synapse-data-couchbase"
