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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;

/**
 * {@code BaseClient} class specifies the prototypes for all clients.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @param <H> httpHeadersFactory used to set the HTTP headers for each web service call
 * @author Paolo Claudio
 */
abstract class BaseClient<I extends BaseClientRequest, O extends BaseClientResponse, H extends BaseClientHttpHeadersFactory<I>> {

	/**
     * HTTP method of the back end service.
     */
    protected HttpMethod httpMethod;
	
	/**
     * URL of the back end service injected from the application properties.
     */
    protected String url;

    /**
     * HTTP headers factory used to produce the custom HTTP headers required to consume the back end services.
     */
    @Autowired
    protected H httpHeadersFactory;

    /**
     * Used to create query parameters for the URI.
     */
    @Autowired
    protected QueryParameterUriCreator queryParameterUriCreator;

    /**
     * Used to create path variables for the URI.
     */
    @Autowired
    protected PathVariableUriCreator pathVariableUriCreator;
    
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
}
