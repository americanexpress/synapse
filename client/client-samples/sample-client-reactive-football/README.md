# sample-client-reactive-football

## Description
- This is the sample client reactive football that utilizes the synapse client reactive to consume the football squiggle RESTful API. It provides an implementation leveraging the synapse reactive client to support
  functionalities like:

    - A proxy implementation for the `BaseReactiveRestClientConfig` with implementation for the `defaultClientConnector()` to support the restful call via proxy.
   - A `FootballReactiveResponseErrorHandler` that extends `BaseRestResponseErrorHandler` that handles the errors generated when calling the external
      football restful API. This error handler has several default functionalities but is also open to extension. The
      functionalities are:
        - Log at error level the body and status code of the error returned. - Throw a `HttpClientErrorException` when an
          error of the 4XX family occurs - Throw a `HttpServerErrorException` when an error of the 5XX family occurs.

## Usage
- To utilize the proxy implementation, have a configuration class that extends `BaseReactiveRestClientConfig` for reactive support.
  - Using the `defaultClientConnector()` provide the implementation for the default client connector to support the restful call via proxy.
  - For example: 
  ```
    @Override
    protected ReactorClientHttpConnector defaultClientConnector() {
        HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .host(this.clientProxyHost)
                        .port(this.clientProxyPort));
        return new ReactorClientHttpConnector(httpClient);
    }
  ```

## Examples
Examples of utilizing the proxy configuration can be found in the following modules:
- sample-client-reactive-footballl
