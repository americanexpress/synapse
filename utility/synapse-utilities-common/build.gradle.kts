dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    testImplementation(project(":framework:synapse-framework-test"))

    implementation(libs.commons.codec)
    implementation(libs.commons.io)
    api(libs.commons.lang3)
    api(libs.commons.collections4)
    implementation(libs.google.http.client)
    api(libs.jackson.databind)
    implementation(libs.jackson.dataformat.xml)
    implementation(libs.jackson.module.jaxb.annotations)
    implementation(libs.jasypt)
    api(libs.openpojo)
    implementation(libs.spring.context)
    implementation(libs.spring.test)
    api(libs.spring.boot.starter.json)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.validation.api)
    api(libs.slf4j.ext)
}

description = "synapse-utilities-common"
