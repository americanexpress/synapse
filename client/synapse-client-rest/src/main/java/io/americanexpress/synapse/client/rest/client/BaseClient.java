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

import java.lang.reflect.ParameterizedType;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code BaseClient} class specifies the prototypes for all clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @author Paolo Claudio
 */
abstract class BaseClient<I extends BaseClientRequest, O extends BaseClientResponse> {

	/**
     * Logger used for this client.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());
	
    /**
     * Client request type which is determined from the generic type argument <I>.
     */
    protected Class<I> clientRequestType;
    
    /**
     * Client response type which is determined from the generic type argument <O>.
     */
    protected Class<O> clientResponseType;
	
	/**
     * HTTP method of the back end service.
     */
    protected HttpMethod httpMethod;
	
	/**
     * URL of the back end service injected from the application properties.
     */
    protected String url;
    
    /**
     * Argument constructor creates a new instance of BaseClient with given values.
     * @param httpMethod HTTP method of the back end service
     */
    protected BaseClient(HttpMethod httpMethod) {
    	this.httpMethod = httpMethod;
    	initialize();
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
     * Initialize this client.
     */
    @SuppressWarnings("unchecked")
    private void initialize() {
    	
    	// Find the first child class of BaseClient
    	// in order to determine the request and response types
    	Class<?> classType = getClass();
    	while(classType.getSuperclass() != BaseClient.class) {
    		classType = classType.getSuperclass();
    	}
    	
    	// Set the client request and response types
    	// which were found from the generic type arguments of BaseClient<0 = I, 1 = O, 2 = H>
    	// Here, the client request type is from the 0th generic type argument <I>
    	// and the client response type is from the 1st generic type argument <O>
    	ParameterizedType parameterizedType = ((ParameterizedType) getClass().getGenericSuperclass());
    	clientRequestType = (Class<I>) parameterizedType.getActualTypeArguments()[0];
    	clientResponseType = (Class<O>) parameterizedType.getActualTypeArguments()[1];
    }
}
