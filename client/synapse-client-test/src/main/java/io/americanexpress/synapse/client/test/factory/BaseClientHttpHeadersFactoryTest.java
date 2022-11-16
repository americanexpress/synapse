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
package io.americanexpress.synapse.client.test.factory;

import io.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.ClientRouting;
import io.americanexpress.synapse.client.rest.model.ClientTrace;
import io.americanexpress.synapse.client.test.client.BaseClientTest;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseClientHttpHeadersFactoryTest} is for testing {@link BaseClientHttpHeadersFactory}
 *
 * @param <I> the type parameter
 * @param <H> the type parameter
 */
public abstract class BaseClientHttpHeadersFactoryTest<I extends BaseClientRequest,
        H extends BaseClientHttpHeadersFactory<I>> extends BaseClientTest {

    @Autowired
    private H httpHeadersFactory;

    protected void setUrl(String url) {
        this.url = url;
    }
    
    @Test
    void create_givenNullArguments_expectedNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(null, null, null), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenNullClientHeaders_expectedNullPointerException() {
        I clientRequest = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(null, clientRequest, url), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenNullClientRequest_expectedNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(new ClientHeaders(), null, url), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenEmptyClientHeaders_expectedNullPointerException() {
        I clientRequest = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(new ClientHeaders(), clientRequest, url), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenEmptyClientHeadersRouting_expectedNullPointerException() {
        ClientHeaders headers = new ClientHeaders();
        headers.setTrace(new ClientTrace());
        I clientRequest = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(headers, clientRequest, url), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenEmptyClientHeadersTrace_expectedNullPointerException() {
        ClientHeaders headers = new ClientHeaders();
        headers.setRouting(new ClientRouting());
        I clientRequest = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(headers, clientRequest, url), CommonAssertionMessages.EXCEPTION_NOT_THROWN);
    }

    @Test
    void create_givenValidClientHeaders_expectedValidHttpHeaders() throws Exception {
        ClientHeaders clientHeaders = getDefaultClientHeaders();
        I clientRequest = mockDefaultClientRequest();
        String url = "https://example.com";
        HttpHeaders actual = httpHeadersFactory.create(clientHeaders, clientRequest, url);
        Assertions.assertNotNull(actual, CommonAssertionMessages.VALUE_NOT_NULL);
    }
    
    protected abstract void initialize(String url);
    
    protected abstract ClientHeaders getDefaultClientHeaders() throws Exception;

    protected abstract I mockDefaultClientRequest();
}
