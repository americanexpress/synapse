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
package io.americanexpress.synapse.service.reactive.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.framework.api.docs.ApiDocsConfig;
import io.americanexpress.synapse.framework.exception.config.ExceptionConfig;
import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * {@code BaseServiceReactiveRestConfig} sets common configurations for the service layer.
 * @author Francois Gutt
 */
@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "io.americanexpress.synapse.service.reactive")
@Import({ExceptionConfig.class, ApiDocsConfig.class, UtilitiesCommonConfig.class})
public class BaseReactiveServiceRestConfig implements WebFluxConfigurer {

    /**
     * Default object mapper.
     */
    private final ObjectMapper defaultObjectMapper;

    /**
     * Constructor taking in objectMapper.
     * @param defaultObjectMapper   the default object mapper.
     */
    @Autowired
    public BaseReactiveServiceRestConfig(ObjectMapper defaultObjectMapper) {
        this.defaultObjectMapper = defaultObjectMapper;
    }

    /**
     * Configures the HTTP message readers and writers for reading from the request body and
     * for writing to the response body in annotated controllers and functional endpoints.
     * @param configurer the service codec configurer
     */
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(getObjectMapper()));
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(getObjectMapper()));
    }

    /**
     * Returns default objectMapper.
     * @return an object mapper
     */
    protected ObjectMapper getObjectMapper() {
        return defaultObjectMapper;
    }
}
