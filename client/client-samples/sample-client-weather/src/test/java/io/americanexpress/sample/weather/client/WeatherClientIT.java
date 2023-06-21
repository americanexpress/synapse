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
package io.americanexpress.sample.weather.client;

import io.americanexpress.sample.client.weather.client.WeatherClient;
import io.americanexpress.sample.client.weather.client.WeatherClientHeadersFactory;
import io.americanexpress.sample.client.weather.model.WeatherRequest;
import io.americanexpress.sample.client.weather.model.WeatherResponse;
import io.americanexpress.sample.weather.config.WeatherClientTestConfig;
import io.americanexpress.synapse.client.test.client.BaseRestClientIT;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

/**
 * {@code WeatherClientIT} tests the WeatherClient.
 */
@ContextConfiguration(classes = WeatherClientTestConfig.class)
class WeatherClientIT extends BaseRestClientIT<WeatherRequest, WeatherResponse, WeatherClientHeadersFactory, WeatherClient> {

    /**
     * Weather client
     */
    private final WeatherClient client;

    /**
     * Instantiates a new Weather client IT.
     *
     * @param client the client
     */
    @Autowired
    WeatherClientIT(WeatherClient client) {
        this.client = client;
    }

    @Test
    @Override
    protected void callMonoService_givenValidRequest_expectedSuccessResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        WeatherResponse response = client.callMonoService(headers, null);
        Assertions.assertNotNull(response, CommonAssertionMessages.RESPONSE_IS_NULL);
    }
}
