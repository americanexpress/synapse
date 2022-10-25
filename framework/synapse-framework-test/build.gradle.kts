dependencies {
    implementation(project(":framework:synapse-framework-logging"))
    api("org.junit.jupiter:junit-jupiter-api:5.9.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    api("org.junit.vintage:junit-vintage-engine:5.9.1")
    api("org.junit.platform:junit-platform-runner:1.9.1")
    implementation("org.mockito:mockito-junit-jupiter:4.8.0")
    api("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.slf4j:slf4j-ext:1.7.36")
    implementation("com.openpojo:openpojo:0.9.1")
    api("org.junit.platform:junit-platform-launcher:1.9.1")
}

description = "synapse-framework-test"
