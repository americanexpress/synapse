dependencies {
    implementation(project(":function:synapse-function"))
    compileOnly("com.amazonaws:aws-lambda-java-events:2.0.2")
    compileOnly("com.amazonaws:aws-lambda-java-core:1.1.0")
}

description = "sample-function-greeter-aws"
