dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.engine)
    api(libs.junit.platform.launcher)
    api(libs.junit.platform.runner)
    api(libs.junit.vintage.engine)
    api(libs.mockito.junit.jupiter)
    api(libs.openpojo)
    api(libs.slf4j.ext)
    api(libs.spring.boot.starter.test)
}

description = "synapse-framework-test"
