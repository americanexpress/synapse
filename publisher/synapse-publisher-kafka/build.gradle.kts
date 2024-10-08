dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.spring.kafka)
}

description = "synapse-publisher-kafka"
