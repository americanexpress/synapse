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
package io.americanexpress.synapse.client.rest.config;

import io.americanexpress.synapse.client.rest.client.BaseReactiveRestClient;
import io.americanexpress.synapse.client.rest.helper.ReactiveRestClientLoggingExchangeFilterFunction;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * {@code BaseReactiveRestClientConfig} class specifies the prototypes for setting the configurations
 * for reactive REST clients.
 *
 * @author Paolo Claudio
 */
@Import(BaseClientConfig.class)
@Configuration
public abstract class BaseReactiveRestClientConfig extends BaseClientConfig {
	
    /**
     * Initialize the client.
     *
     * @param destinationUrl             of the provider
     * @param reactiveRestClient         used to connect to the provider
     */
    @SuppressWarnings("rawtypes")
    protected void initializeClient(String destinationUrl, BaseReactiveRestClient reactiveRestClient) {

        // Set the destination URL for the client
        reactiveRestClient.setUrl(destinationUrl);
        
        // Set the web client for the reactive REST client
        WebClient webClient = defaultWebClient(destinationUrl);
        reactiveRestClient.setWebClient(webClient);
    }
    
    /**
     * Generate the default web client.
     * @param destinationUrl of the provider
     * @return the default web client
     */
    public WebClient defaultWebClient(String destinationUrl) {
        return WebClient.builder()
    		.filter(ReactiveRestClientLoggingExchangeFilterFunction.logClientRequest())
    		.filter(ReactiveRestClientLoggingExchangeFilterFunction.logClientResponse())
                .clientConnector(defaultClientConnector())
        	.baseUrl(destinationUrl)
        	.build();
    }

    /**
     * Generate the default http client connector.
     * @return the default http client connector
     */
    protected ReactorClientHttpConnector defaultClientConnector() {
        return new ReactorClientHttpConnector();
    }
}
