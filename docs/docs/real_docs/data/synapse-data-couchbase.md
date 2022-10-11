# synapse-data-couchbase

## Description

- This is the synapse couchbase abstraction framework used whenever there is a need to connect to couchbase database and
  read from it. It provides several out-of-the-box functionalities like:

    - Built-in read functionalities to create multi-parameter and paginated dynamic queries based on Spring Data and
      QueryDSL.(There is not other library like this out-there, it still needs some work).
    - An open to extension base configuration java file with the standard connection parameters needed to connect to
      couchbase using Spring Data. The connection parameters will be provided by the property files on the module(s)that
      you as a developer are creating that will use this base-data-postgres module as a dependency.
    - An open to extension BaseEntity containing the generated key identifier and the common auditing fields maintained
      by the Spring Data framework itself (createdBy, lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-couchbase</artifactId>
            <version>0.3.1-SNAPSHOT</version>
        </dependency>
```