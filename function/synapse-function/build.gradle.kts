dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api(project(":framework:synapse-framework-api-docs"))
    api(project(":framework:synapse-framework-test"))
    api("org.junit.jupiter:junit-jupiter-api:5.9.1")
    api("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    api("io.swagger:swagger-annotations:1.6.6")
    api("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    api("org.springframework.security:spring-security-test:5.7.3")
    api("org.springframework.boot:spring-boot-starter:2.7.4")
    api("org.springframework.boot:spring-boot-starter-data-rest:2.7.4")
    api("org.springframework.boot:spring-boot-starter-web:2.7.4")
    api("org.springframework.boot:spring-boot-starter-aop:2.7.4")
    api("org.springframework.boot:spring-boot-starter-test:2.7.4")
    api("org.springframework.boot:spring-boot-starter-actuator:2.7.4")
}

description = "synapse-function"
