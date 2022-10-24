dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    implementation(project(":utility:synapse-utilities-common"))
    implementation(project(":framework:synapse-framework-test"))
    implementation("org.slf4j:slf4j-ext:1.7.36")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.4")
}

description = "synapse-client-rest"
