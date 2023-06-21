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
package io.americanexpress.sample.client.reactive.weather.client;

import io.americanexpress.sample.client.reactive.weather.config.WeatherReactiveClientTestConfig;
import io.americanexpress.sample.client.reactive.weather.model.WeatherRequest;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.synapse.client.test.client.BaseGetReactiveRestClientIT;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * {@code WeatherReactiveClientIT} tests the {@link WeatherReactiveClient}.
 */
@ContextConfiguration(classes = WeatherReactiveClientTestConfig.class)
@ExtendWith(SpringExtension.class)
class WeatherReactiveClientIT extends BaseGetReactiveRestClientIT<WeatherRequest, WeatherResponse, WeatherReactiveClientHeadersFactory, WeatherReactiveClient> {
    @Override
    protected String mockPathVariables() {
        return null;
    }

    @Override
    protected HttpHeaders getDefaultClientHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    protected WeatherRequest mockDefaultClientRequest() {
        return null;
    }

    @Override
    protected WeatherRequest mockInvalidClientRequest() {
        return null;
    }
}
