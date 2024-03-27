# synapse-data-db2

## Description

- This is the synapse db2 abstraction framework used whenever there is a need to connect to
  db2 database. It provides several out-of-the-box functionalities like:
    - Connection to DB2 database through spring-data-mongodb and configurable 
  using properties files with the following required properties:
      - spring.datasource.url
      - spring.datasource.username
      - spring.datasource.password
      - spring.datasource.driver-class-name
      - spring.jpa.properties.hibernate.dialect
      - spring.jpa.hibernate.ddl-auto
  - An open to extension BaseEntity that contains the key identifier

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-db2</artifactId>
            <version>0.3.32-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-db2:0.3.32-SNAPSHOT'
```
- Have a configuration class that import or extends `BaseDb2Config`.
- Add the `@EnableJpaRepositories(basePackages = PACKAGE_NAME)`.
- For repositories, extend the `BaseCrudDb2Repository`.


## Examples
Examples of utilizing the synapse-data-mongodb module can be found in the following modules:
- sample-data-db2-book
