# synapse-data-oracle

## Description

- This is the synapse oracle abstraction framework used whenever the re is a need to connect to
  oracle database. It provides several out-of-the-box functionalities like:

    - Connection to oracle database through spring-data-oracle and configurable
      using properties files with the following required properties:
       - spring.oracle.datasource.username
       - spring.oracle.datasource.password
       - spring.oracle.datasource.url
       - spring.oracle.datasource.serviceName
       - spring.oracle.datasource.port
       - spring.oracle.datasource.driverType

    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-oracle</artifactId>
            <version>0.3.19-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-oracle:0.3.8'
```

- Have a configuration class that import or extends `BaseOracleDataConfig` or `BaseReactiveOracleDataConfig` for reactive support.
- Add the `@EnableJpaRepositories(basePackages = PACKAGE_NAME)` or `@EnableR2dbcRepositories(basePackages = PACKAGE_NAME)` for reactive support.
- For repositories, extend the `JpaRepository` or `R2dbcRepository` for reactive support.

## Examples
Examples of utilizing the synapse-data-oracle module can be found in the following modules:
- sample-data-oracle-book
- sample-data-oracle-reactive-book
- sample-service-rest-oracle-book
- sample-service-reactive-oracle-book
