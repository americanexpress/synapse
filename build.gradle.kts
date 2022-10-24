plugins {
    `java-library`
    `maven-publish`
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "io.americanexpress.synapse"
    version = "0.3.3-SNAPSHOT"

    apply(plugin = "java-library")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
