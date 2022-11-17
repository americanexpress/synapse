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
package io.americanexpress.sample.client.reactive.weather.config;

import io.americanexpress.sample.client.reactive.weather.client.WeatherReactiveClient;
import io.americanexpress.sample.client.reactive.weather.handler.WeatherReactiveResponseErrorHandler;
import io.americanexpress.synapse.client.rest.config.BaseReactiveRestClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code WeatherClientConfig} contains configurations for weather client.
 */
@Configuration
@ComponentScan("io.americanexpress.sample.client.reactive.weather")
@PropertySource("classpath:client-weather.properties")
public class WeatherReactiveClientConfig extends BaseReactiveRestClientConfig {

    /**
     * Reactive client for making call to weather api.
     */
    private final WeatherReactiveClient weatherReactiveClient;

    /**
     * Reactive response error handler for weather client.
     */
    private final WeatherReactiveResponseErrorHandler weatherReactiveResponseErrorHandler;

    /**
     * Instantiates a new Weather reactive client config.
     *
     * @param weatherReactiveClient               the weather reactive client
     * @param weatherReactiveResponseErrorHandler the weather reactive response error handler
     */
    public WeatherReactiveClientConfig(WeatherReactiveClient weatherReactiveClient, WeatherReactiveResponseErrorHandler weatherReactiveResponseErrorHandler) {
        this.weatherReactiveClient = weatherReactiveClient;
        this.weatherReactiveResponseErrorHandler = weatherReactiveResponseErrorHandler;
    }

    @Value("${client.url}")
    @Override
    protected void initialize(String destinationUrl) {
        initializeClient(destinationUrl, weatherReactiveClient);
    }
}
