# synapse-service-test

## Description

- This is the synapse module that provides the base classes to test the controllers. It provides several out-of-the-box
  functionalities like:

    - An open to extension BaseControllerTest class with several overloaded methods create the slice test methods of any
      possible restful call made to your controllers.
    - An open to extension BaseControllerIT class with several overloaded methods to create the integration test methods
      of any possible restful call made to your controllers.

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-service-test</artifactId>
            <version>0.3.19-SNAPSHOT</version>
        </dependency>