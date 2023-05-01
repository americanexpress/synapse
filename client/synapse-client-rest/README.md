# synapse-client-rest

## Description
- This is the synapse gateway framework utilized to consume RESTful APIs. It provides several out-of-the-box
  functionalities like:

    - An open to extension BaseRestClient with several overloaded methods for every single possible restful call.
    - A configurable ClientLoggingInterceptor that logs the request and response parameters at a configurable log level.
    - An open to extension BaseRestResponseErrorHandler that handles the errors generated when calling the external
      restful APIs. This error handler has several default functionalities but is also open to extension. The
      functionalities are:
        - Log at error level the body and status code of the error returned. - Throw a HttpClientErrorException when an
          error of the 4XX family occurs - Throw a HttpServerErrorException when an error of the 5XX family occurs.
    - An open to extension generic hmac generator class. (Not sure if this applies to American Express only).

## Usage
- To utilize this module, add the following dependency to the pom.xml file:
```
        <dependency>
            <groupId>com.americanexpress</groupId>
            <artifactId>synapse-client-rest</artifactId>
            <version>0.3.14-SNAPSHOT</version>
        </dependency>
```
Or add the following to the build.gradle file:
```
implementation 'io.americanexpress.synapse:synapse-client-rest:0.3.8-SNAPSHOT'
```

- Have a configuration class that extends `BaseRestClientConfig` or `BaseReactiveRestClientConfig` for reactive support.
  - Using the `initialize()` initialize the client with the url for the API
  - For example: 
  ```
    @Value("${client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, weatherClient, weatherResponseErrorHandler);
    }
  ```
- Create the client class extending `BaseRestClient` or one of the follow base reactive client classes for reactive support:
  - BaseGetReactiveRestClient
  - BasePostReactiveRestClient
  - BasePutReactiveRestClient
  - BaseDeleteReactiveRestClient

## Examples
Examples of utilizing the synapse-client-rest module can be found in the following modules:
- sample-client-weather
