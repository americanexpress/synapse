dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api(project(":framework:synapse-framework-api-docs"))
    api(project(":framework:synapse-framework-test"))
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.engine)
    api(libs.spring.boot.starter)
    api(libs.spring.boot.starter.aop)
    api(libs.spring.boot.starter.actuator)
    api(libs.spring.boot.starter.data.rest)
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.webflux)
    api(libs.spring.boot.starter.test)
    api(libs.spring.security.test)
    api(libs.swagger.annotations)
}

description = "synapse-function"
