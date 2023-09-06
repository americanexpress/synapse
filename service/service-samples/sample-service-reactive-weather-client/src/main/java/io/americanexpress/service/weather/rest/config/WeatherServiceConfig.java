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
package io.americanexpress.service.weather.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.sample.client.reactive.weather.config.WeatherReactiveClientConfig;
import io.americanexpress.synapse.service.reactive.rest.config.BaseServiceReactiveRestConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code WeatherServiceConfig} is the configuration class for the Weather Application.
 */
@Configuration
@PropertySource("classpath:/service-weather-application.properties")
@ComponentScan(basePackages = "io.americanexpress.service.weather.rest")
@Import({WeatherReactiveClientConfig.class})
public class WeatherServiceConfig extends BaseServiceReactiveRestConfig {

    /**
     * Constructor taking in objectMapper & metricInterceptor
     *
     * @param defaultObjectMapper the default object mapper.
     * @param interceptor         the metric interceptor.
     */
    public WeatherServiceConfig(ObjectMapper defaultObjectMapper) {
        super(defaultObjectMapper);
    }
}
