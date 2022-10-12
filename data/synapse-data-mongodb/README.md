# synapse-data-mongodb

## Description

- This is the synapse mongodb abstraction framework used whenever there is a need to connect to
  mongodb database. It provides several out-of-the-box functionalities like:

    - Connection to MongoDB database through spring-data-mongodb and configurable 
  using properties files with the following required properties:
      - spring.data.mongodb.uri 
      - spring.data.mongodb.database
      
    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-mongodb</artifactId>
            <version>0.3.2</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-mongodb:0.3.2!!'
```

- Have a configuration class that import or extends `BaseMongoDBDataConfig` or `BaseReactiveMongoDBDataConfig` for reactive support.
- Add the `@EnableMongoRepositories(basePackages = PACKAGE_NAME)` or `@EnableReactiveMongoRepositories(basePackages = PACKAGE_NAME)` for reactive support.
- For repositories, extend the `MongoRepository` or `ReactiveMongoRepository` for reactive support.

## Examples
Examples of utilizing the synapse-data-mongodb module can be found in the following modules: 
  - sample-data-mongodb-book
  - sample-data-mongodb-reactive
  - sample-service-rest-mongodb-book
