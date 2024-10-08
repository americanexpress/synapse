dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    implementation(project(":framework:synapse-framework-logging"))

    implementation(libs.lombok)
    implementation(libs.jackson.databind)
    implementation(libs.java.client)
    implementation(libs.slf4j.ext)
    implementation(libs.spring.core)
    implementation(libs.spring.data.couchbase)
    implementation(libs.javax.persistence.api)
}

description = "synapse-data-couchbase"
