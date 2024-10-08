dependencies {
    api(project(":data:synapse-data-cassandra"))
    api(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
}

description = "sample-data-cassandra-reactive"
