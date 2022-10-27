plugins {
    `java-library`
    `maven-publish`
    //springdoc-openapi-maven-plugin
//    id("org.springframework.boot") version "2.7.0"
//    id("org.springdoc.openapi-gradle-plugin") version "1.4.0"
    //kotlin-maven-plugin
//    id("org.jetbrains.kotlin.jvm") version "1.2.60"
//    id("org.jetbrains.kotlin.kapt") version "1.2.60"
//    id("org.jetbrains.kotlin.plugin.allopen") version "1.2.60"
//    id("org.jetbrains.kotlin.plugin.spring") version "1.2.60"
    //jacoco maven plugin
    id("jacoco")
    // maven checkstyle plugin
    checkstyle
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"

}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    group = "io.americanexpress.synapse"
    version = "0.3.3-SNAPSHOT"

    apply(plugin = "java-library")
}

configure(subprojects) {
    apply<MavenPublishPlugin>()
    apply<SigningPlugin>()

    configure<JavaPluginExtension> {
    }

    configure<SigningExtension> {
        val key = System.getenv("SIGNING_KEY") ?: return@configure
        val password = System.getenv("SIGNING_PASSWORD") ?: return@configure
        val publishing: PublishingExtension by project

        useInMemoryPgpKeys(key, password)
        sign(publishing.publications)
    }


    configure<PublishingExtension> {
        publications {
            val main by creating(MavenPublication::class) {
                from(components["java"])

                pom {
                    name.set("synapse :: ${project.name}")
                    description.set("synapse :: ${project.name}")
                    url.set("https://github.com/americanexpress/synapse")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("./LICENSE")
                        }
                    }
                    developers {
                        developer {
                            name.set("American Express Travel Related Services, Inc.")
                            organizationUrl.set("https://americanexpress.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/americanexpress/synapse.git")
                        developerConnection.set("scm:git:ssh://git@github.com/americanexpress/synapse.git")
                        url.set("https://github.com/americanexpress/synapse")
                    }
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        create("myNexus") {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/releases/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set("your-username") // defaults to project.properties["myNexusUsername"]
            password.set("your-password") // defaults to project.properties["myNexusPassword"]
        }
    }
}

tasks.test {
    useJUnit()
    exclude("**/**IT**")
}
