dependencies {
    implementation(project(":function:synapse-function"))
    implementation("org.springframework.cloud:spring-cloud-function-adapter-gcp:3.2.7")
}

description = "sample-function-greeter-gcp"
