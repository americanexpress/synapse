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
package io.americanexpress.synapse.client.rest.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * {@code RestClientLoggingCustomizer} class is used to handle the intercepting
 * of the client requests and responses for logging.
 *
 * @author Paolo Claudio
 */
@Component
public class RestClientLoggingCustomizer implements RestTemplateCustomizer {

	/**
	 * Used to intercept the client request and response.
	 */
    private final RestClientLoggingInterceptor restClientLoggingInterceptor;

    /**
     * Argument constructor creates a new instance of RestClientLoggingCustomizer with given values.
     * @param restClientLoggingInterceptor used to intercept the client request and response
     */
    @Autowired
    public RestClientLoggingCustomizer(RestClientLoggingInterceptor restClientLoggingInterceptor) {
        this.restClientLoggingInterceptor = restClientLoggingInterceptor;
    }

    /**
     * Customize the rest template.
     *
     * @param restTemplate the rest template
     */
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
        restTemplate.getInterceptors().add(restClientLoggingInterceptor);
    }
}
