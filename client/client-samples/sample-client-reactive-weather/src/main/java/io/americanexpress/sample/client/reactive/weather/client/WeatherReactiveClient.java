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

import io.americanexpress.sample.client.reactive.weather.model.WeatherRequest;
import io.americanexpress.sample.client.reactive.weather.model.WeatherResponse;
import io.americanexpress.synapse.client.rest.client.BaseGetReactiveRestClient;
import io.americanexpress.synapse.client.rest.client.ReactiveRestClient;
import io.americanexpress.synapse.client.rest.handler.BaseReactiveRestResponseErrorHandler;

/**
 * {@code WeatherReactiveClient} contains configurations for reactive weather client.
 */
@ReactiveRestClient
public class WeatherReactiveClient extends BaseGetReactiveRestClient<WeatherRequest, WeatherResponse, WeatherReactiveClientHeadersFactory> {

    /**
     * Argument constructor creates a new instance of BaseGetReactiveRestClient with given values.
     *
     * @param httpHeadersFactory               HTTP headers factory used to produce the custom HTTP headers required to consume the back end service
     * @param reactiveRestResponseErrorHandler used to handle errors from the reactive REST client
     */
    protected WeatherReactiveClient(WeatherReactiveClientHeadersFactory httpHeadersFactory, BaseReactiveRestResponseErrorHandler reactiveRestResponseErrorHandler) {
        super(httpHeadersFactory, reactiveRestResponseErrorHandler);
    }
}
