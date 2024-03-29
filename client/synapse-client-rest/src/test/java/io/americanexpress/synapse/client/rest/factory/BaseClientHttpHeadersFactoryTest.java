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
package io.americanexpress.synapse.client.rest.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * {@code BaseClientHttpHeadersFactoryTest} tests the {@link BaseClientHttpHeadersFactory}
 */
class BaseClientHttpHeadersFactoryTest {

    @Test
    void httpHeadersFactory_constructor() {

        // Mock an instance of the abstract factory using its default constructor
        mock(BaseClientHttpHeadersFactory.class, withSettings().useConstructor(new ObjectMapper()));
    }

    @Test
    void create_null() {

        @SuppressWarnings("unchecked")
        BaseClientHttpHeadersFactory<BaseClientRequest> factory = (BaseClientHttpHeadersFactory<BaseClientRequest>) mock(BaseClientHttpHeadersFactory.class);
        when(factory.create(any(HttpHeaders.class), any(BaseClientRequest.class), any())).thenReturn(null);
        HttpHeaders actual = factory.create(null, null, null);
        assertNull(actual, "HTTP headers are null.");
    }
}
