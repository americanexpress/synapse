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

import io.americanexpress.synapse.client.rest.helper.UrlBuilder;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import io.americanexpress.synapse.client.rest.model.QueryParameter;
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
 * @author Paolo Claudio
 */
public abstract class BaseRestClient<I extends BaseClientRequest, O extends BaseClientResponse> extends BaseClient<I, O> {

    /**
     * Template for performing REST operations using default serialization options set in the BaseRestClientConfig.
     * This can be overridden in child classes if different serialization options need to be set while connecting to back end services.
     */
    protected RestTemplate restTemplate;

    /**
     * Argument constructor creates a new instance of BaseRestClient with given values.
     * @param httpMethod HTTP method of the back end service
     */
    protected BaseRestClient(HttpMethod httpMethod) {
		super(httpMethod);
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
     * @param headers      headers for the back end service
     * @param clientRequest      body of the request
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public O callMonoService(HttpHeaders headers, I clientRequest, String... pathVariables) {
        return callMonoService(headers, clientRequest, null, pathVariables);
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param headers      headers for the back end service
     * @param clientRequest      body of the request
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public O callMonoService(HttpHeaders headers, I clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(headers, clientRequest, queryParameters, pathVariables);

        // Call the service
        ResponseEntity<?> responseEntity = restTemplate.exchange(requestEntity, clientResponseType);

        // Cast the class type of the response
        @SuppressWarnings("unchecked")
        O responseBody = (O) responseEntity.getBody();
        return responseBody;
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param headers      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public List<O> callPolyService(HttpHeaders headers, I clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, String... pathVariables) {
        return callPolyService(headers, clientRequest, responseEntityType, null, pathVariables);
    }

    /**
     * Get the list of responses from the service given the HTTP headers and request body.
     *
     * @param headers      headers for the back end service
     * @param clientRequest      body of the request
     * @param responseEntityType type of client response
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the back end service
     */
    public List<O> callPolyService(HttpHeaders headers, I clientRequest, ParameterizedTypeReference<List<O>> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(headers, clientRequest, queryParameters, pathVariables);

        // Call the service
        ResponseEntity<List<O>> responseEntity = restTemplate.exchange(requestEntity, responseEntityType);
        return responseEntity.getBody();
    }
    
    /**
     * Create the request entity used for the back end service.
     *
     * @param headers   headers for the back end service
     * @param clientRequest   body of the request
     * @param queryParameters parameters needed to be added to URI
     * @param pathVariables   to add to the URI
     * @return the request entity for the back end service
     */
    private RequestEntity<I> createRequestEntity(HttpHeaders headers, I clientRequest, List<QueryParameter> queryParameters, String... pathVariables) {
        
    	// Get the updated URL which may change in each client request due to path variables and/or query parameters
    	String updatedUrl = UrlBuilder.build(url, queryParameters, pathVariables);
        URI uri = URI.create(updatedUrl);

        // Create the request
        return new RequestEntity<>(clientRequest, headers, httpMethod, uri);
    }
}
