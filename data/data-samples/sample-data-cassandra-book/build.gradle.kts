dependencies {
    api(project(":data:synapse-data-cassandra"))
    api(libs.spring.boot.starter.test)
    api(libs.spring.boot.test.autoconfigure)
    testImplementation(libs.testcontainers)
    testImplementation(libs.cassandra)
    testImplementation(libs.testcontainers.junit.jupiter)
}

description = "sample-data-cassandra-book"
