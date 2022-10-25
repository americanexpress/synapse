dependencies {
    implementation(libs.commons.lang3)
    implementation(libs.postgresql)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.data.rest)
    implementation(libs.spring.context.support)
    implementation(libs.h2)
    implementation(libs.ehcache)
    implementation(libs.hibernate.ehcache)
}

description = "synapse-data-postgres"
