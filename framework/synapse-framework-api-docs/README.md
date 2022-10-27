# synapse-framework-api-docs

## Description

- This is the synapse module used for api documentation. This module is essentially a wrapper around swagger-ui, where
  it is already hooked on Synapse Rest framework, to provide Swagger UI out of the box for any rest api's built
  utilizing Synapse.

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-framework-api-docs</artifactId>
            <version>0.3.3-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-framework-api-docs:0.2.1!!'
```
