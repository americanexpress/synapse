# synapse-function-rest

## Description

- This synapse module is the gateway framework module used to expose Restful APIs. It provides several out-of-the-box
  functionalities like:

    - An open to extension generic set of abstract controller classes to easily build any concrete CRUD
      Controller by simply extending the needed base CRUD Controller class.
    - An open to extension configuration file that support for most of the available media types for RESTful
      webservices. If needed, new ones could be added. Also provides a default ObjectMapper for its serialization and
      deserialization. This can be overriden.
    - An open to extension generic ControllerExceptionHandler that handles the most common types of errors happening in
      an application.
    - An open to extension way of handling input validations.
    - An open to extension generic MetricInterceptor that will log the response time, status code and correlation
      identifier for every request.
    - Open to extensions base request and response model to leave the code open to extension and close to modifications.
    - A generic already implemented pagination solution out of the box when calling a db.
    - A common error response object following standard naming of error fields.

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-service-rest</artifactId>
            <version>0.3.30-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-service-rest:0.2.1!!'
```
