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
package io.americanexpress.sample.client.weather.config;

import io.americanexpress.sample.client.weather.client.WeatherClient;
import io.americanexpress.sample.client.weather.handler.WeatherResponseErrorHandler;
import io.americanexpress.synapse.client.rest.config.BaseRestClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code WeatherClientConfig} contains configurations for weather client.
 */
@Configuration
@ComponentScan("io.americanexpress.sample.client.weather")
@PropertySource("classpath:client-weather.properties")
public class WeatherClientConfig extends BaseRestClientConfig {

    /**
     * Client for making call to weather api.
     */
    private final WeatherClient weatherClient;

    /**
     * Response error handler for weather client.
     */
    private final WeatherResponseErrorHandler weatherResponseErrorHandler;

    /**
     * Instantiates a new Weather client config.
     *
     * @param weatherClient               the weather client
     * @param weatherResponseErrorHandler the weather response error handler
     */
    public WeatherClientConfig(WeatherClient weatherClient, WeatherResponseErrorHandler weatherResponseErrorHandler) {
        this.weatherClient = weatherClient;
        this.weatherResponseErrorHandler = weatherResponseErrorHandler;
    }

    @Value("${client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, weatherClient, weatherResponseErrorHandler);
    }
}
