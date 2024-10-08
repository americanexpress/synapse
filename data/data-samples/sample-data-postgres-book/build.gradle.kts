dependencies {
    implementation(project(":data:synapse-data-postgres"))
    implementation(project(":framework:synapse-framework-test"))
    implementation(libs.h2)
    implementation(libs.mockito.junit.jupiter)
    implementation(libs.postgresql)
    implementation(libs.querydsl.apt)
    implementation(libs.querydsl.jpa)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.test)
}

description = "sample-data-postgres-book"
