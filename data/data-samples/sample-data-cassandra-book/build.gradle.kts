dependencies {
    api(project(":data:synapse-data-cassandra"))
    api("org.springframework.boot:spring-boot-starter-test:2.7.4")
    api("org.springframework.boot:spring-boot-test-autoconfigure:2.7.4")
    testImplementation("org.testcontainers:testcontainers:1.17.5")
    testImplementation("org.testcontainers:cassandra:1.17.5")
    testImplementation("org.testcontainers:junit-jupiter:1.17.5")
}

description = "sample-data-cassandra-book"
