# synapse-data-mysql

## Description

- This is the synapse mysql abstraction framework used whenever the re is a need to connect to
  mysql database. It provides several out-of-the-box functionalities like:

    - Connection to mysql database through spring-data-mysql and configurable
      using properties files with the following required properties:
       - spring.mysql.datasource.username
       - spring.mysql.datasource.password
       - spring.mysql.datasource.url
       - spring.mysql.datasource.serviceName
       - spring.mysql.datasource.port
       - spring.mysql.datasource.driverType

    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-mysql</artifactId>
            <version>0.3.14-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-mysql:0.3.8'
```

- Have a configuration class that import or extends `BaseMySqlDataConfig` or `BaseReactiveMySqlConfig` for reactive support.
- Add the `@EnableJpaRepositories(basePackages = PACKAGE_NAME)` or `@EnableR2dbcRepositories(basePackages = PACKAGE_NAME)` for reactive support.
- For repositories, extend the `JpaRepository` or `R2dbcRepository` for reactive support.

## Examples
Examples of utilizing the synapse-data-mysql module can be found in the following modules:
- sample-data-mysql-book
- sample-data-mysql-reactive-book
- sample-service-rest-mysql-book
- sample-service-reactive-mysql-book
