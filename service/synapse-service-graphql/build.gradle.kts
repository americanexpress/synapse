dependencies {
    api(project(":framework:synapse-framework-logging"))
    api(project(":framework:synapse-framework-exception"))
    api(libs.graphiql.spring.boot.starter)
    api(libs.graphql.java.extended.scalars)
    api(libs.spring.boot.starter.webflux)
    api(libs.voyager.spring.boot.starter)
}

description = "synapse-service-graphql"
