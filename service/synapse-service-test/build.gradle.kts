dependencies {
    implementation(project(":service:synapse-service-rest"))
    implementation(project(":framework:synapse-framework-test"))
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-json:2.7.4")
    implementation("org.springframework.security:spring-security-core:5.7.3")
    implementation("org.springframework.security:spring-security-test:5.7.3")
    implementation("com.github.stefanbirkner:system-rules:1.19.0")
    implementation("org.mockito:mockito-core:4.8.0")
}

description = "synapse-service-test"
