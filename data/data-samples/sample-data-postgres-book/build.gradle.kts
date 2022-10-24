dependencies {
    implementation(project(":data:synapse-data-postgres"))
    implementation(project(":framework:synapse-framework-test"))
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.4")
    implementation("com.h2database:h2:2.1.214")
    implementation("org.mockito:mockito-junit-jupiter:4.8.0")
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    implementation("org.springframework.boot:spring-boot-starter-security:2.7.4")
    implementation("com.querydsl:querydsl-apt:5.0.0")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
}

description = "sample-data-postgres-book"
