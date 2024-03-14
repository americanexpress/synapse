dependencies {
    api(project(":framework:synapse-framework-exception"))
    api(project(":utility:synapse-utilities-common"))
    api(project(":framework:synapse-framework-test"))

    implementation(libs.slf4j.ext)
    implementation(libs.spring.boot.starter.webflux)
}

description = "synapse-client-rest"
