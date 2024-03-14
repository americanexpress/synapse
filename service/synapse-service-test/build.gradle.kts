dependencies {
    api(project(":service:synapse-service-rest"))
    api(project(":framework:synapse-framework-test"))

    implementation(libs.mockito.core)
    implementation(libs.spring.boot.starter.json)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.security.core)
    implementation(libs.spring.security.test)
    implementation(libs.system.rules)
}

description = "synapse-service-test"
