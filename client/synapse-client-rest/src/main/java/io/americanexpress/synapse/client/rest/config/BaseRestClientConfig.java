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
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @deprecated as of 0.4.18, replaced by {@link #initializeClient(String, BaseRestClient, BaseRestResponseErrorHandler, long, long, int)}
     *
     * @param destinationUrl             of the provider
     * @param restClient                 used to connect to the provider
     * @param restResponseErrorHandler   used to handle errors from the provider
     */
    @Deprecated(since = "0.4.18")
    protected void initializeClient(String destinationUrl, BaseRestClient<?,?,?> restClient, BaseRestResponseErrorHandler restResponseErrorHandler) {
        // Set the destination URL for the client
        restClient.setUrl(destinationUrl);

        // Set the rest template for the REST client
        RestTemplate restTemplate = defaultRestTemplate();
        restTemplate.setErrorHandler(restResponseErrorHandler);
        restClient.setRestTemplate(restTemplate);
    }

    /**
     * Initialize the client with the given url, connect timeout, read timeout and max connections.
     *
     * @param destinationUrl             of the provider.
     * @param restClient                 used to connect to the provider.
     * @param restResponseErrorHandler   used to handle errors from the provider.
     * @param connectTimeoutMillis       connection timeout in milliseconds.
     * @param readTimeoutMillis          read timeout in milliseconds.
     * @param maxConnections             maximum number of connections.
     */
    protected void initializeClient(String destinationUrl, BaseRestClient<?,?,?> restClient, BaseRestResponseErrorHandler restResponseErrorHandler, long connectTimeoutMillis, long readTimeoutMillis, int maxConnections) {
        // Set the destination URL for the client
        restClient.setUrl(destinationUrl);

        // Set the rest template for the REST client
        var restTemplate = defaultRestTemplate(connectTimeoutMillis, readTimeoutMillis, maxConnections);
        restTemplate.setErrorHandler(restResponseErrorHandler);
        restClient.setRestTemplate(restTemplate);
    }

    /**
     * Generate the default REST template.
     *
     * @deprecated as of 0.4.18, replaced by {@link #defaultRestTemplate(long, long, int)}
     *
     * @return the default REST template
     */
    @Deprecated(since = "0.4.18")
    public RestTemplate defaultRestTemplate() {
        return defaultRestTemplate(2000, 10000, 20);
    }

    /**
     * Generate the default REST template.
     *
     * @return the default REST template
     */
    public RestTemplate defaultRestTemplate(long connectTimeoutMillis, long readTimeoutMillis, int maxConnections) {
        var connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnTotal(maxConnections)
                .setMaxConnPerRoute(maxConnections)
                .build();

        var httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .build();

        var requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectTimeout(Duration.ofMillis(connectTimeoutMillis));
        requestFactory.setReadTimeout(Duration.ofMillis(readTimeoutMillis));

        var messagesConverters = new ArrayList<HttpMessageConverter<?>>();
        var converter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));
        messagesConverters.add(converter);

        return new RestTemplateBuilder()
                .customizers(restClientLoggingCustomizer)
                .messageConverters(messagesConverters)
                .requestFactory(() -> requestFactory)
                .build();
    }
}
