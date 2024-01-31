# synapse-data-mssql

## Description

- This is the synapse Microsoft SQL abstraction framework used whenever there is a need to connect to
  Microsoft SQL database. It provides several out-of-the-box functionalities like:

    - Connection to Microsoft SQL database through r2dbc-mssql and configurable
      using properties files with the following required properties:
        - spring.r2dbc.url
        - spring.r2dbc.username
        - spring.r2dbc.password

    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-mssql</artifactId>
            <version>0.3.31-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-mssql:0.3.14-SNAPSHOT'
```

- Have a configuration class that import or extends `BaseReactiveMicrosoftSQLDataConfig` for reactive support.
- Add the `@EnableR2dbcRepositories(basePackages = PACKAGE_NAME)` for reactive support.
- For repositories, extend the `R2dbcRepository` for reactive support.

## Examples
Examples of utilizing the synapse-data-oracle module can be found in the following modules:
- sample-data-mssql-reactive-book
- sample-service-reactive-mssql-book
