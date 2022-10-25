dependencies {
    implementation(libs.junit.jupiter.api)
    implementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.platform.launcher)
    implementation(libs.junit.platform.runner)
    implementation(libs.junit.vintage.engine)
    implementation(libs.log4j.api)
    implementation(libs.log4j.core)
    api(libs.slf4j.ext)
    implementation(libs.spring.boot.starter.log4j2)
}

description = "synapse-framework-logging"
