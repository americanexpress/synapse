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
package io.americanexpress.synapse.client.test.client;

import io.americanexpress.synapse.client.rest.client.BaseReactiveRestClient;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.BaseClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseReactiveRestClientIT} is the base test class for reactive client integration tests.
 *
 * @param <I> the input type parameter
 * @param <O> the output type parameter
 * @param <C> the reactive client type parameter
 */
public abstract class BaseReactiveRestClientIT<I extends BaseClientRequest,
        O extends BaseClientResponse,
        C extends BaseReactiveRestClient<I, O>> extends BaseClientTest {

    /**
     * Initialize.
     */
    @BeforeEach
    protected void initialize() {
        this.url = client.getUrl();
    }

    /**
     * The Rest client.
     */
    @Autowired
    protected C client;

    /**
     * The Client request.
     */
    protected I clientRequest;

    /**
     * Gets default client headers.
     *
     * @return the default client headers
     */
    protected abstract HttpHeaders getDefaultClientHeaders();

    /**
     * Mock default client request .
     *
     * @return the mock client request of type I
     */
    protected abstract I mockDefaultClientRequest();

    /**
     * Mock invalid client request .
     *
     * @return the mock invalid client request of type I
     */
    protected abstract I mockInvalidClientRequest();

}
