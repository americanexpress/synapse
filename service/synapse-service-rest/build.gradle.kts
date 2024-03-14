configurations {
    implementation {
        // Needs to be excluded, so that framework-logging doesn"t get override by Spring Starter Logger.
        exclude(group= "org.springframework.boot", module= "spring-boot-starter-logging")
    }
}

dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api(project(":framework:synapse-framework-api-docs"))
    api(project(":framework:synapse-framework-test"))
    api(project(":utility:synapse-utilities-common"))

    implementation(libs.commons.lang3)
    implementation(libs.junit.jupiter.api)
    implementation(libs.junit.jupiter.engine)
    implementation(libs.mockito.junit.jupiter)
    api(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.aop)
    api(libs.spring.boot.starter.data.rest)
    implementation(libs.spring.boot.starter.test)
    api(libs.spring.boot.starter.validation)
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.webflux)
    implementation(libs.spring.plugin.core)
    implementation(libs.spring.security.test)
    implementation(libs.swagger.annotations)
    implementation(libs.tomcat.embed.core)
}

description = "synapse-service-rest"

tasks.test {
    useJUnitPlatform()
    exclude("**/**Test**")
}
