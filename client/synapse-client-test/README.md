# synapse-client-test

## Description

- This is the synapse module that provides the base classes to test the spring restful clients. It provides several
  out-of-the-box functionalities like:

    - An open to extension BaseRestClientUnitTest class which already calls several methods to unit test all the
      scenarios in the concrete client unit test class that extend this.
    - An open to extension BaseRestClientUnitIT class that will require only the concrete rest client IT class
      implementing it to override one success method which will be the integration test method needed.

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-client-test</artifactId>
            <version>0.3.30-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-client-test:0.3.30-SNAPSHOT'
```

- For testing a client extend the `BaseRestClientUnitTest` and implement the overridden methods.
- 
## Examples
Examples of utilizing the synapse-client-test module can be found in the following modules:
- sample-client-weather

