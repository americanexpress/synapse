# synapse-data-postgres

## Description

- This is the synapse data relational database abstraction framework used whenever there is a need to connect to
  postgres database. It provides several out-of-the-box functionalities like:

    - An open to extension base configuration java file open to extension that provides the connection logic to connect
      to any relational database and create a hikari connection pool. The connection parameters will be provided by
      property files on the modules using this module.
    - Open to extension property files with the default following values:
        - An open to extension connection pool default parameters. - An open to extension H2 connection parameters for
          local property file. - An open to extens4ion H2 initialization parameters for local property file. - An open
          to extension any relational database initialization parameters for three extra environments(E1, E2 and E3).

    - An open to extension BaseEntity that contains the key identifier with a default(open to extension also)
      GenerationType.IDENTITY and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-postgres</artifactId>
            <version>0.3.31-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-postgres:0.2.1!!'
```
