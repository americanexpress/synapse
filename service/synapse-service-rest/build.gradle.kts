dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api(project(":framework:synapse-framework-api-docs"))
    api(project(":framework:synapse-framework-test"))
    api(project(":utility:synapse-utilities-common"))
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.mockito:mockito-junit-jupiter:4.8.0")
    api("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
    implementation("org.springframework.security:spring-security-test:5.7.3")
    implementation("org.springframework.plugin:spring-plugin-core:2.0.0.RELEASE")
    api("org.springframework.boot:spring-boot-starter:2.7.4")
    api("org.springframework.boot:spring-boot-starter-data-rest:2.7.4")
    api("org.springframework.boot:spring-boot-starter-web:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-aop:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.7.4")
    api("org.springframework.boot:spring-boot-starter-validation:2.7.4")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.62")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("io.swagger:swagger-annotations:1.6.6")
}

description = "synapse-service-rest"

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
        exclude("**/**IT**")
    }
}