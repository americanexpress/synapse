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
package io.americanexpress.synapse.client.rest.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ClientLoggingCustomizer class is only to add the BufferingClientHttpRequestFactory to the ClientLoggingInterceptor so the response could be read twice.
 *
 * @author Paolo Claudio
 */
@Component
public class ClientLoggingCustomizer implements RestTemplateCustomizer {

    private final ClientLoggingInterceptor clientLoggingInterceptor;

    @Autowired
    public ClientLoggingCustomizer(ClientLoggingInterceptor clientLoggingInterceptor) {
        this.clientLoggingInterceptor = clientLoggingInterceptor;
    }

    /**
     * Add the BufferingClientHttpRequestFactory to the ClientLoggingInterceptor.
     *
     * @param restTemplate
     */
    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
        restTemplate.getInterceptors().add(clientLoggingInterceptor);
    }
}
