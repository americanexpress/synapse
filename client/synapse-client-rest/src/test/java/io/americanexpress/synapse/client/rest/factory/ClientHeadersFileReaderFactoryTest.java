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

import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import io.americanexpress.synapse.client.rest.model.ClientRouting;
import io.americanexpress.synapse.client.rest.model.ClientTrace;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@code ClientHeadersFileReaderFactoryTest} tests the {@link ClientHeadersFileReaderFactory}
 */
class ClientHeadersFileReaderFactoryTest {

    @Test
    void clientHeadersFactory_constructor() throws Exception {
        Constructor<ClientHeadersFileReaderFactory> constructor = ClientHeadersFileReaderFactory.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);
    }

    private ClientHeaders getDefaultClientHeaders() {
        ClientHeaders headers = new ClientHeaders();
        headers.setRouting(new ClientRouting());
        headers.setTrace(new ClientTrace());
        return headers;
    }

    private ClientHeaders getSomeClientHeaders() {
        ClientHeaders headers = new ClientHeaders();
        ClientRouting routing = new ClientRouting();
        headers.setRouting(routing);
        headers.setTrace(new ClientTrace());
        return headers;
    }

    private ClientHeaders getFullClientHeaders() {
        ClientHeaders headers = new ClientHeaders();
        ClientTrace trace = new ClientTrace();
        trace.setCorrelationId("1234");
        ClientRouting routing = new ClientRouting();
        headers.setTrace(trace);
        headers.setRouting(routing);
        return headers;
    }

    @Test
    void create_nullClasspath() {
        assertThrows(IllegalArgumentException.class, () -> ClientHeadersFileReaderFactory.create(null));
    }

    @Test
    void create_nonexistentFile() {
        assertThrows(FileNotFoundException.class, () -> ClientHeadersFileReaderFactory.create("nonexistent"));
    }

    @Test
    void create_otherFileExtension() throws Exception {
        ClientHeaders actual = ClientHeadersFileReaderFactory.create("sample-other-extension.txt");
        ClientHeaders expected = getFullClientHeaders();
        assertEquals(expected, actual, "Headers are not equal.");
    }

    @Test
    void create_someHeaders() throws Exception {
        ClientHeaders actual = ClientHeadersFileReaderFactory.create("sample-some-headers.properties");
        ClientHeaders expected = getSomeClientHeaders();
        assertEquals(expected, actual, "Headers are not equal.");
    }

    @Test
    void create_validAndUnknownHeaders() throws Exception {
        ClientHeaders actual = ClientHeadersFileReaderFactory.create("sample-valid-and-unknown-headers.properties");
        ClientHeaders expected = getSomeClientHeaders();
        assertEquals(expected, actual, "Headers are not equal.");
    }

    @Test
    void create_allHeaders() throws Exception {
        ClientHeaders actual = ClientHeadersFileReaderFactory.create("sample-all-headers.properties");
        ClientHeaders expected = getFullClientHeaders();
        assertEquals(expected, actual, "Headers are not equal.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"sample-empty-file.properties", "sample-malformed-headers.properties", "sample-empty-header-values.properties",
       "sample-empty-header-values.properties", "sample-unknown-headers.properties"})
    void create(String path) throws IOException {
        ClientHeaders actual = ClientHeadersFileReaderFactory.create(path);
        ClientHeaders expected = getDefaultClientHeaders();
        assertEquals(expected, actual, "Headers are not equal.");
    }

}
