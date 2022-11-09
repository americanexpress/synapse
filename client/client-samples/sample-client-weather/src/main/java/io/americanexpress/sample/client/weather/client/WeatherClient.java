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
package io.americanexpress.sample.client.weather.client;

import io.americanexpress.sample.client.weather.model.WeatherRequest;
import io.americanexpress.sample.client.weather.model.WeatherResponse;
import io.americanexpress.synapse.client.rest.client.BaseRestClient;
import io.americanexpress.synapse.client.rest.client.RestClient;
import org.springframework.http.HttpMethod;

/**
 * {@code WeatherClient} for making an api call to a weather api.
 */
@RestClient
public class WeatherClient extends BaseRestClient<WeatherRequest, WeatherResponse> {

    /**
     * Argument constructor creates a new instance of BaseRestClient with given values.
     */
    protected WeatherClient() {
        super(HttpMethod.GET);
    }

}
