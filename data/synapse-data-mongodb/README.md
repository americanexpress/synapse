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
            <version>0.3.8-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-mongodb:0.2.1!!'
```
