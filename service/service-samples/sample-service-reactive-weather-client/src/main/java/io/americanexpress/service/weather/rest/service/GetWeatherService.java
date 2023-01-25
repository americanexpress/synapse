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
import io.americanexpress.sample.client.reactive.weather.model.WeatherRequest;
import io.americanexpress.service.weather.rest.model.GetWeatherResponse;
import io.americanexpress.service.weather.rest.model.WeatherCode;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code GetWeatherService} is the service for getting the current temperature and weather.
 */
@Service
public class GetWeatherService extends BaseGetMonoReactiveService<GetWeatherResponse> {

    private final WeatherReactiveClient weatherReactiveClient;

    /**
     * Instantiates a new Get weather service.
     *
     * @param weatherReactiveClient the weather reactive client
     */
    public GetWeatherService(WeatherReactiveClient weatherReactiveClient) {
        this.weatherReactiveClient = weatherReactiveClient;
    }

    @Override
    protected Mono<GetWeatherResponse> executeRead(HttpHeaders headers, String request) {
        return weatherReactiveClient.callMonoService(new HttpHeaders(), new WeatherRequest())
                .map(weatherResponse -> {
                    GetWeatherResponse getWeatherResponse = new GetWeatherResponse();
                    CurrentWeather currentWeather = weatherResponse.getCurrentWeather();
                    if(currentWeather != null) {
                        getWeatherResponse.setTemperature(currentWeather.getTemperature());
                        getWeatherResponse.setWeather(WeatherCode.getWeatherCode(currentWeather.getWeatherCode()));
                    }
                    return getWeatherResponse;
                });
    }
}
