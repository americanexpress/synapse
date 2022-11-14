# synapse-data-cassandra

## Description

- This is the synapse cassandra abstraction framework used whenever there is a need to connect to
  cassandra database. It provides several out-of-the-box functionalities like:

    - Connection to Cassandra database through spring-data-cassandra and configurable
      using properties files with the following required properties:
        - spring.data.cassandra.keyspace-name
        - spring.data.cassandra.local-datacenter
        - spring.data.cassandra.port
        - spring.data.cassandra.contact-points
        - spring.data.cassandra.schema-action

    - An open to extension BaseEntity that contains the key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy,
      lastModifiedBy, createdDate, lastModifiedDate and version).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-data-cassandra</artifactId>
            <version>0.3.2</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-data-cassandra:0.3.2'
```

- Have a configuration class that import or extends `BaseCassandraDataConfig` or `BaseReactiveCassandraDataConfig` for reactive support.
- Add the `@EnableCassandraRepositories(basePackages = PACKAGE_NAME)` or `@EnableReactiveCassandraRepositories(basePackages = PACKAGE_NAME)` for reactive support.
- For repositories, extend the `CassandraRepository` or `ReactiveCassandraRepository` for reactive support.

## Examples
Examples of utilizing the synapse-data-cassandra module can be found in the following modules:
- sample-data-cassandra-book
- sample-data-cassandra-reactive
- sample-service-rest-cassandra-book
- sample-service-reactive-cassandra-book
