dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.junit.vintage:junit-vintage-engine:5.9.1")
    implementation("org.junit.platform:junit-platform-runner:1.9.1")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.7.4")
    api("org.slf4j:slf4j-ext:1.7.36")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-api:2.19.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.1")
}

description = "synapse-framework-logging"
