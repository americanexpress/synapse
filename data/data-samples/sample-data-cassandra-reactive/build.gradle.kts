dependencies {
    api(project(":data:synapse-data-cassandra"))
    api("org.springframework.boot:spring-boot-starter-test:2.7.4")
    testImplementation("io.projectreactor:reactor-test:3.4.23")
}

description = "sample-data-cassandra-reactive"
