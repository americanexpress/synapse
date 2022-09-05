# synapse-data-mongodb

## Description

- This is the synapse mongodb abstraction framework used whenever there is a need to connect to
  mongodb database. It provides several out-of-the-box functionalities like:

    - Default connection to local MongoDB test database through spring-boot-starter-data-mongodb and configurable 
  using properties files with the following values:
      - spring.data.mongodb.host
      - spring.data.mongodb.port
      - spring.data.mongodb.database
      - spring.data.mongodb.username
      - spring.data.mongodb.password
      
    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-mongodb</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-mongodb:0.2.1!!'
```
