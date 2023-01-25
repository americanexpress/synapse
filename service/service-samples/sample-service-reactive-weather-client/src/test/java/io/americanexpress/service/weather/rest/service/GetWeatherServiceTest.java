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
package io.americanexpress.service.weather.rest.service;

import io.americanexpress.sample.client.reactive.weather.client.WeatherReactiveClient;
import io.americanexpress.sample.client.reactive.weather.model.CurrentWeather;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.service.weather.rest.config.WeatherServiceConfig;
import io.americanexpress.service.weather.rest.model.GetWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * {@code GetWeatherServiceTest} tests the {@link GetWeatherService}.
 */
@ContextConfiguration(classes = WeatherServiceConfig.class)
@ExtendWith(MockitoExtension.class)
class GetWeatherServiceTest {

    /**
     * Used to mock the {@link WeatherReactiveClient} api call
     */
    @Mock
    private WeatherReactiveClient weatherReactiveClient;

    /**
     * Service to be tested
     */
    @InjectMocks
    private GetWeatherService getWeatherService;

    @Test
    void executeRead_givenRequestAndValidClientResponse_expectedResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemperature(39);
        currentWeather.setWeatherCode(1);
        weatherResponse.setCurrentWeather(currentWeather);

        Mockito.when(weatherReactiveClient.callMonoService(Mockito.any(), Mockito.any())).thenReturn(Mono.just(weatherResponse));

        Mono<GetWeatherResponse> getWeatherResponseMono = getWeatherService.executeRead(new HttpHeaders(), "1");
        StepVerifier.create(getWeatherResponseMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void executeRead_givenRequestAndClientResponseNoCurrentWeather_expectedResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();

        Mockito.when(weatherReactiveClient.callMonoService(Mockito.any(), Mockito.any())).thenReturn(Mono.just(weatherResponse));

        Mono<GetWeatherResponse> getWeatherResponseMono = getWeatherService.executeRead(new HttpHeaders(), "1");
        StepVerifier.create(getWeatherResponseMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

}
