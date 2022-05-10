# Synapse

- Tired of having the developers in your team not following the same standards and each of them doing things their own way? 
- Tired of having a disorganized architecture almost impossible to maintain after just a few months of starting your project?
- Tired of struggling on how to start a new project and setting up all the foundational architecture? 
- Tired of spending countless hours, days, or months in getting your foundational frameworks robust and reliable? 

If any of these sound like situations you have experienced or experiences that you simply want to avoid, then Synapse is what you need.

Synapse is a set of lightweight modules designed to help developers build out applications. The modules serve as a wide range tool-suite for developers to facilitate rapid development, with high quality built-in.
It is designed with the purpose of helping developers create web services in a quick and easy way following strict conventions.
Synapse provides an abstraction layer that enforces developers to follow SOLID principles and avoid common mistakes during the development process.
It is based on keeping things SIMPLE and clear. The framework strongly encourages convention over configuration, while highlighting the criticality of structure. 
Although Synapse will cover a majority of the scenarios you will need when creating an enterprise-grade application, it is also very open to extension. 
We encourage you to extend and implement the abstraction layer of this framework. Furthermore, if you feel the need to create a new feature not covered here, you also have that flexibility.

## 📖 Table of Contents

- [Quick Start](#-quick-start)
- [Documentation](#-documentation)
- [License](#%EF%B8%8F-license)
- [Code Of Conduct](#%EF%B8%8F-code-of-conduct)
- [Community](#community)
- [Contributing](#-contributing)

## Synapse Architecture
![Synapse Architecture](docs/assets/synapse-architecture.png)

### Synapse Modules:
#### synapse-service-rest
- This synapse module is the gateway framework module used to expose RESTful APIs. 
It provides several out-of the box functionalities like:

    - An open to extension generic set of open to extension five base crud controller classes to easily build any CRUD Controller by just extending the required base CRUD Controller class.
    - An open to extension generic set of open to extension five base crud service classes to easily build any CRUD Concrete Service class by just extending the required base CRUD Service class and overriding the protected abstract method by it.
    - An open to extension configuration file that support for most of the available media types for RESTful webservices. If needed, new ones could be added. Also provides a default ObjectMapper for its serialization and deserialization. This can be overriden also.
    - An open to extension generic ControllerExceptionHandler that handles the most common types of errors happening in an application.
    - An open to extension way of handling input validations.
    - An open to extension generic MetricInterceptor that will log the response time, status code and correlation identifier for every request.
    - Open to extensions base request and response model to leave the code open to extension and close to modifications.
    - A generic already implemented pagination solution out of the box when calling a db.
    - A common error response object following standard naming of error fields.

#### synapse-client-rest
- This is the synapse gateway framework utilized to consume RESTful APIs. 
It provides several out-of-the-box functionalities like:

    - An open to extension BaseRestClient with several overloaded methods for every single possible restful call.
    - A configurable ClientLoggingInterceptor that logs the request and response parameters at a configurable log level.
    - An open to extension BaseRestResponseErrorHandler that handles the errors generated when calling the external restful APIs. This error handler has several default functionalities but is also open to extension. The functionalities are:
            - Log at error level the body and status code of the error returned.
            - Throw a HttpClientErrorException when an error of the 4XX family occurs
            - Throw a HttpServerErrorException when an error of the 5XX family occurs. 
    - An open to extension generic hmac generator class. (Not sure if this applies to American Express only).
    
#### synapse-client-soap
- This is the synapse gateway framework utilized to consume SOAP web services. 
It provides several out-of-the-box functionalities like:

    - An open to extension BaseSoapClient with several overloaded methods for every single possible soap call.

#### synapse-data-couchbase
- This is the synapse couchbase abstraction framework used whenever there is a need to connect to couchbase database and read from it.
It provides several out-of-the-box functionalities like:

    - Built-in read functionalities to create multi-parameter and paginated dynamic queries based on Spring Data and QueryDSL.(There is not other library like this out-there, it still needs some work).
    - An open to extension base configuration java file with the standard connection parameters needed to connect to couchbase using Spring Data. The connection parameters will be provided by the property files on the module(s) that you as a developer are creating that will use this base-data-postgres module as a dependency.
    - An open to extension BaseEntity containing the generated key identifier and the common auditing fields maintained by the Spring Data framework itself (createdBy, lastModifiedBy, createdDate, lastModifiedDate and version). 
    
#### synapse-data-postgres
- This is the synapse data relational database abstraction framework used whenever there is a need to connect to postgres database. 
It provides several out-of-the-box functionalities like:

     - An open to extension base configuration java file open to extension that provides the connection logic to connect to any relational database and create a hikari connection pool. The connection parameters will be provided by property files on the modules using this module.
     - Open to extension property files with the default following values: 
                - An open to extension connection pool default parameters.
                - An open to extension H2 connection parameters for local property file.
                - An open to extens4ion H2 initialization parameters for local property file.
                - An open to extension any relational database initialization parameters for three extra environments(E1, E2 and E3).

     - An open to extension BaseEntity that contains the key identifier with a default(open to extension also) GenerationType.IDENTITY and the common auditing fields maintained by the Spring Data framework itself (createdBy, lastModifiedBy, createdDate, lastModifiedDate and version). 

#### synapse-framework-exception
- This is the synapse module that provides the two Exception classes you will ever need in your application and also an elegant mechanism to handle them.
It provides several out-of-the-box functionalities like:

    - An ApplicationServerException to wrap the caught checked exceptions thrown by the application.
    - An ApplicationClientException to return a user-friendly message to the users when a warning or message needs to be returned.
    - A generic open to extension ErrorCode enum with the most common error codes used by any application.
    - A generic open to extension error-messages.properties file that uses the error codes from the ErrorCode enum mentioned above.

#### synapse-service-test
- This is the synapse module that provides the base classes to test the controllers.
It provides several out-of-the-box functionalities like:
    
    - An open to extension BaseControllerTest class with several overloaded methods create the slice test methods of any possible restful call made to your controllers.
    - An open to extension BaseControllerIT class with several overloaded methods to create the integration test methods of any possible restful call made to your controllers.

#### synapse-client-test
- This is the synapse module that provides the base classes to test the spring restful clients.
It provides several out-of-the-box functionalities like:
    
    - An open to extension BaseRestClientUnitTest class which already calls several methods to unit test all the scenarios in the concrete client unit test class that extend this.
    - An open to extension BaseRestClientUnitIT class that will require only the concrete rest client IT class implementing it to override one success method which will be the integration test method needed.

#### synapse-framework-api-docs
- This is the synapse module used for api documentation. 
This module is essentially a wrapper around swagger-ui, where it is already hooked on Synapse Rest framework, to provide Swagger UI out of the box for any rest api's built utilziing Synapse.    


#### synapse-utilities-common
- Encapsulates the common utility classes that can be leveraged by any module.
It provides several out-of-the-box functionalities like:

    - A default object mapper defined in its configuration file
-----

## Benefits on-top Spring 
- The Synapse framework is an extension of Spring that uses best practices to force compliance to good standards, while also simplifying the development process. 
These are several of the benefits it provides on-top of what Spring already provides:
    - Creates a very structured and organized architecture very easy to follow by anyone, junior and senior developers.
    - Enforces developers to follow the same template and good standards across the entire code base.
    - Forces strict separation of concerns because the base 'Hook' classes which the developers extend from are already representing each layer in the famous and already proven three layer architecture.
            - Base<Crud functionality>Controller (Http Layer).
            - Base<Crud functionality>Service (Sevice Layer).
            - BaseRestClient, BaseSoapClient or Repositories(These are already interfaces so not base classes provided) (DAO Layer).
    - Provides the BaseControllerTest class with a set of overloaded methods to facilitate controllers slice testing.
    - Provides a couchbase library to build dynamic queries based on Spring Data and Query DSL (There is nothing like this out there).
    - Provides a ControllersExceptionHandlers that handles most of the common exceptions.
    - Provides an elegant and very simple to use exception handling mechanism with only two custom runtime exceptions that covers all the possible scenarios.
    - Provides a generic way to connect to any relational database, using h2 for local and unit tests. And leave free the use of any desired relational database for other environments.
