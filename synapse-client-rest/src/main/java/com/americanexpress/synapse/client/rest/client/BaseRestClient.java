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
package com.americanexpress.synapse.client.rest.client;

import com.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import com.americanexpress.synapse.client.rest.model.BaseClientRequest;
import com.americanexpress.synapse.client.rest.model.BaseClientResponse;
import com.americanexpress.synapse.client.rest.model.ClientHeaders;
import com.americanexpress.synapse.client.rest.model.QueryParameter;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * <code>BaseRestClient</code> class is the parent for all REST clients.
 *
 * @param <I> input request responseType
 * @param <O> output response responseType
 * @param <H> httpHeadersFactory used for each backend service call
 * @author Paolo Claudio
 */
public abstract class BaseRestClient<I extends BaseClientRequest, O extends BaseClientResponse, H extends BaseClientHttpHeadersFactory<I>> {

    /**
     * Logger used for this client.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Template for performing REST operations using default serialization options set in the BaseRestClientConfig.
     * This can be overridden in child classes if different serialization options need to be set while connecting to backend services.
     */
    protected RestTemplate restTemplate;

    /**
     * URL of the backend service injected from the application properties.
     */
    protected String url;

    /**
     * HTTP method of the backend service.
     */
    protected HttpMethod httpMethod;

    /**
     * HTTP headers factory used to produce the custom HTTP headers required to consume the backend services.
     */
    @Autowired
    protected H httpHeadersFactory;

    /**
     * Used to create query parameters for the URI.
     */
    @Autowired
    private QueryParameterUriCreator queryParameterUriCreator;

    /**
     * Used to create path variables for the URI.
     */
    @Autowired
    private PathVariableUriCreator pathVariableUriCreator;

    /**
     * Default constructor creates an instance of BaseRestClient with default values.
     */
    public BaseRestClient() {
        setHttpMethod(HttpMethod.POST);
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
     * Set the httpMethod.
     *
     * @param httpMethod the httpMethod to set
     */
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * Get the httpMethod.
     *
     * @return the httpMethod
     */
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    /**
     * Get the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url.
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Create the request entity used for the backend service.
     *
     * @param clientHeaders   headers for the backend service
     * @param request         body of the request
     * @param queryParameters parameters needed to be added to URI
     * @param pathVariables   to add to the URI
     * @return the request entity for the backend service
     */
    protected RequestEntity<I> createRequestEntity(ClientHeaders clientHeaders, I request, List<QueryParameter> queryParameters, String... pathVariables) {
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append(pathVariableUriCreator.createPathVariableUri(pathVariables));
        urlBuilder.append(queryParameterUriCreator.createQueryParameterUri(queryParameters));
        String updatedUrl = urlBuilder.toString();
        URI uri = URI.create(updatedUrl);

        // Create the HTTP headers for the service
        HttpHeaders httpHeaders = httpHeadersFactory.create(clientHeaders, request, updatedUrl);

        // Create the request
        return new RequestEntity<>(request, httpHeaders, httpMethod, uri);
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the backend service
     * @param request            body of the request
     * @param responseEntityType type of client response
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the backend service
     */
    public O callService(ClientHeaders clientHeaders, I request, Class<?> responseEntityType, String... pathVariables) {
        return callService(clientHeaders, request, responseEntityType, null, pathVariables);
    }

    /**
     * Get the response from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the backend service
     * @param request            body of the request
     * @param responseEntityType type of client response
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the backend service
     */

    public O callService(ClientHeaders clientHeaders, I request, Class<?> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(clientHeaders, request, queryParameters, pathVariables);

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
     * @param clientHeaders      headers for the backend service
     * @param request            body of the request
     * @param responseEntityType type of client response
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the backend service
     */
    public List<O> callService(ClientHeaders clientHeaders, I request, ParameterizedTypeReference<List<O>> responseEntityType, String... pathVariables) {
        return callService(clientHeaders, request, responseEntityType, null, pathVariables);
    }

    /**
     * Get the list of responses from the service given the HTTP headers and request body.
     *
     * @param clientHeaders      headers for the backend service
     * @param request            body of the request
     * @param responseEntityType type of client response
     * @param queryParameters    parameters needed to be added to URI
     * @param pathVariables      variables needed to be added to URI
     * @return the response body from the backend service
     */
    public List<O> callService(ClientHeaders clientHeaders, I request, ParameterizedTypeReference<List<O>> responseEntityType, List<QueryParameter> queryParameters, String... pathVariables) {

        // Create the request
        RequestEntity<I> requestEntity = createRequestEntity(clientHeaders, request, queryParameters, pathVariables);

        // Call the service
        ResponseEntity<List<O>> responseEntity = restTemplate.exchange(requestEntity, responseEntityType);
        return responseEntity.getBody();
    }
}
