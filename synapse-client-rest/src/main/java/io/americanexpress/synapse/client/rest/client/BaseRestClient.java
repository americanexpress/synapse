/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.client.rest.client;

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.QueryParameter;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * {@code BaseRestClient} class specifies the prototypes for all blocking REST clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @author Paolo Claudio
 */
public abstract class BaseRestClient<I extends BaseClientRequest, O extends BaseClientResponse, H extends BaseClientHttpHeadersFactory<I>> extends BaseClient<I, O, H> {

    /**
     * Template for performing REST operations using default serialization options set in the BaseRestClientConfig.
     * This can be overridden in child classes if different serialization options need to be set while connecting to back end services.
     */
    protected RestTemplate restTemplate;

    /**
     * Argument constructor creates a new instance of BaseRestClient with given values.
     * @param httpHeadersFactory HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param httpMethod HTTP method of the back end service
     */
    protected BaseRestClient(H httpHeadersFactory, HttpMethod httpMethod) {
		super(httpHeadersFactory, httpMethod);
	}
    
    /**
     * Get the restTemplate.
     *
     * @return the restTemplate
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    /**
     * Set the restTemplate.
     *
     * @param restTemplate the restTemplate to set
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public O callService(ClientHeaders clientHeaders, I clientRequest, Class<?> responseEntityType, String... pathVariables) {
        return callService(clientHeaders, clientRequest, responseEntityType, null, pathVariables);
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public O callService(ClientHeaders clientHeaders, I clientRequest, Class<?> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(clientHeaders, clientRequest, queryParameters, pathVariables);

        // Call the service
        ResponseEntity<?> responseEntity = restTemplate.exchange(requestEntity, responseEntityType);

        // Cast the class type of the response
        @SuppressWarnings("unchecked")
        O responseBody = (O) responseEntity.getBody();
        return responseBody;
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public List<O> callService(ClientHeaders clientHeaders, I clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, String... pathVariables) {
        return callService(clientHeaders, clientRequest, responseEntityType, null, pathVariables);
    }

    /**
     * Get the list of responses from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public List<O> callService(ClientHeaders clientHeaders, I clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(clientHeaders, clientRequest, queryParameters, pathVariables);

        // Call the service
        ResponseEntity<List<O>> responseEntity = restTemplate.exchange(requestEntity, responseEntityType);
        return responseEntity.getBody();
    }
    
    /**
     * Create the request entity used for the back end service.
     *
     * @param clientHeaders   headers for the back end service
     * @param clientRequest   body of the request
     * @param queryParameters parameters needed to be added to URI
     * @param pathVariables   to add to the URI
     * @return the request entity for the back end service
     */
    private RequestEntity<I> createRequestEntity(ClientHeaders clientHeaders, I clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append(PathVariableUriCreator.create(pathVariables));
        urlBuilder.append(queryParameterUriCreator.createQueryParameterUri(queryParameters));
        String updatedUrl = urlBuilder.toString();
        URI uri = URI.create(updatedUrl);

        // Create the HTTP headers for the service
        HttpHeaders httpHeaders = httpHeadersFactory.create(clientHeaders, clientRequest, updatedUrl);

        // Create the request
        return new RequestEntity<>(clientRequest, httpHeaders, httpMethod, uri);
    }
}
