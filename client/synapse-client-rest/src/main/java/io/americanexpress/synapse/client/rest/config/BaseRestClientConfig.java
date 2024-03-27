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

import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseRestResponseErrorHandler;
import io.americanexpress.synapse.client.rest.helper.RestClientLoggingCustomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * {@code BaseRestClientConfig} class specifies the prototypes for setting the configurations
 * for the REST clients.
 *
 * @author Paolo Claudio
 */
@Configuration
@Import(BaseClientConfig.class)
public abstract class BaseRestClientConfig extends BaseClientConfig {

	/**
     * Used to log the client request and response.
     */
    @Autowired
    protected RestClientLoggingCustomizer restClientLoggingCustomizer;
	
    /**
     * Initialize the client.
     *
     * @param destinationUrl             of the provider
     * @param restClient                 used to connect to the provider
     * @param restResponseErrorHandler   used to handle errors from the provider
     */
    @SuppressWarnings("rawtypes")
    protected void initializeClient(String destinationUrl, BaseRestClient restClient, BaseRestResponseErrorHandler restResponseErrorHandler) {

        // Set the destination URL for the client
        restClient.setUrl(destinationUrl);

        // Set the rest template for the REST client
        RestTemplate restTemplate = defaultRestTemplate();
        restTemplate.setErrorHandler(restResponseErrorHandler);
        restClient.setRestTemplate(restTemplate);
    }
    
    /**
     * Generate the default REST template.
     *
     * @return the default REST template
     */
    public RestTemplate defaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder()
        	.customizers(restClientLoggingCustomizer)
        	.build();
        
        List<HttpMessageConverter<?>> messagesConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        messagesConverters.add(converter);
        restTemplate.setMessageConverters(messagesConverters);
        restTemplate.setRequestFactory(defaultRequestFactory());
        return restTemplate;
    }

    /**
     * Generate the default request factory to allow support for proxy configuration.
     *
     * @return The client http request factory.
     */
    protected ClientHttpRequestFactory defaultRequestFactory() {
        return new SimpleClientHttpRequestFactory();
    }
}
