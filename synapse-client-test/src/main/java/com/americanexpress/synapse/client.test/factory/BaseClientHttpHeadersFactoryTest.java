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
package com.americanexpress.synapse.client.test.factory;

import com.americanexpress.synapse.client.rest.factory.BaseClientHttpHeadersFactory;
import com.americanexpress.synapse.client.rest.model.BaseClientRequest;
import com.americanexpress.synapse.client.rest.model.ClientHeaders;
import com.americanexpress.synapse.client.rest.model.ClientRouting;
import com.americanexpress.synapse.client.rest.model.ClientTrace;
import com.americanexpress.synapse.client.test.BaseClientTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseClientHttpHeadersFactoryTest<I extends BaseClientRequest,
        H extends BaseClientHttpHeadersFactory<I>> extends BaseClientTest {

    @Autowired
    protected H httpHeadersFactory;

    protected abstract ClientHeaders getDefaultClientHeaders() throws Exception;

    protected abstract I mockDefaultClientRequest();

    public abstract void httpHeadersFactory_constructor();

    public abstract void create_noDependenciesInjected() throws Exception;

    protected abstract void initialize(String url);

    protected void setUrl(String url) {
        this.url = url;
    }

    @Test
    public void create_nullArguments() {
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(null, null, null));
    }

    @Test
    public void create_nullClientHeaders() {
        I request = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(null, request, url));
    }

    @Test
    public void create_nullRequest() {
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(new ClientHeaders(), null, url));
    }

    @Test
    public void create_emptyClientHeaders() {
        I request = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(new ClientHeaders(), request, url));
    }

    @Test
    public void create_emptyClientHeadersRouting() {
        ClientHeaders headers = new ClientHeaders();
        headers.setTrace(new ClientTrace());
        I request = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(headers, request, url));
    }

    @Test
    public void create_emptyClientHeadersTrace() {
        ClientHeaders headers = new ClientHeaders();
        headers.setRouting(new ClientRouting());
        I request = mockDefaultClientRequest();
        Assertions.assertThrows(NullPointerException.class, () -> httpHeadersFactory.create(headers, request, url));
    }

    @Test
    public void create_clean() throws Exception {
        ClientHeaders clientHeaders = getDefaultClientHeaders();
        I request = mockDefaultClientRequest();
        String url = "https://example.com";
        httpHeadersFactory.create(clientHeaders, request, url);
    }
}
