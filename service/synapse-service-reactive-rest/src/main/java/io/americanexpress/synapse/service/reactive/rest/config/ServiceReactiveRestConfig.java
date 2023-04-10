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
package io.americanexpress.synapse.service.reactive.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.framework.api.docs.ApiDocsConfig;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Collections;

/**
 * {@code ServiceReactiveRestConfig} sets common configurations for the service layer.
 *
 * @author Elisha Aquino
 */
@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "io.americanexpress.synapse.service.reactive.rest")
@Import({ExceptionConfig.class, ApiDocsConfig.class, UtilitiesCommonConfig.class})
public class ServiceReactiveRestConfig implements WebFluxConfigurer {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;


    /**
     * Constructor taking in objectMapper & metricInterceptor
     * @param defaultObjectMapper   the default object mapper.
     */
    @Autowired
    public ServiceReactiveRestConfig(ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    /**
     * Get the JSON message converter.
     *
     * @return the JSON message converter.
     */
    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        final MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(getObjectMapper());
        messageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return messageConverter;
    }

    /**
     * Returns default objectMapper.
     * @return an object mapper
     */
    protected ObjectMapper getObjectMapper() {
        return defaultObjectMapper;
    }
}
