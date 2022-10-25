dependencies {
    implementation(project(":framework:synapse-framework-exception"))
    api("org.springframework.boot:spring-boot-starter-json:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.springframework:spring-test:5.3.23")
    api("org.apache.commons:commons-lang3:3.12.0")
    api("org.apache.commons:commons-collections4:4.4")
    implementation("com.google.http-client:google-http-client:1.42.2")
    api("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-jaxb-annotations:2.13.4")
    implementation("commons-io:commons-io:2.11.0")
    implementation("javax.validation:validation-api:2.0.1.Final")
    api("org.slf4j:slf4j-ext:1.7.36")
    api("com.openpojo:openpojo:0.9.1")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.jasypt:jasypt:1.9.3")
    implementation("org.springframework:spring-context:5.3.23")
    testImplementation(project(":framework:synapse-framework-test"))
}

description = "synapse-utilities-common"
