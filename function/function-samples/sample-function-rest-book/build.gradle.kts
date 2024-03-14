dependencies {
    implementation(project(":function:synapse-function"))
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    testImplementation("io.projectreactor:reactor-test:3.4.23")
}

description = "sample-function-book"
