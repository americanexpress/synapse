dependencies {
    implementation(project(":framework:synapse-framework-logging"))
    implementation(project(":framework:synapse-framework-exception"))
    implementation(project(":framework:synapse-framework-api-docs"))
    implementation(project(":framework:synapse-framework-test"))
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("io.swagger:swagger-annotations:1.6.6")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    implementation("org.springframework.security:spring-security-test:5.7.3")
    implementation("org.springframework.boot:spring-boot-starter:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-aop:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.4")
}

description = "synapse-function"
