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
package io.americanexpress.synapse.client.rest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientHeadersTest {

    @Test
    public void clientHeaders_constructor() {
        new ClientHeaders();
    }

    @Test
    public void getTrace_null() {
        ClientHeaders headers = new ClientHeaders();
        headers.getTrace();
    }

    @Test
    public void setTrace_null() {
        ClientHeaders headers = new ClientHeaders();
        headers.setTrace(null);
    }

    @Test
    public void getRouting_null() {
        ClientHeaders headers = new ClientHeaders();
        headers.getRouting();
    }

    @Test
    public void setRouting_null() {
        ClientHeaders headers = new ClientHeaders();
        headers.setRouting(null);
    }

    @Test
    public void equals_this() {
        ClientHeaders headers1 = new ClientHeaders();
        ClientHeaders headers2 = headers1;
        assertEquals(headers1, headers2, "Objects are not equal.");
    }

    @Test
    public void equals_null() {
        ClientHeaders headers1 = new ClientHeaders();
        ClientHeaders headers2 = null;
        assertNotEquals(headers1, headers2);
    }

    @Test
    public void equals_invalidType() {
        ClientHeaders headers1 = new ClientHeaders();
        String headers2 = "";
        assertNotEquals(headers1, headers2);
    }

    @Test
    public void equals_instanceContainsNull() {
        ClientHeaders headers1 = new ClientHeaders();
        ClientHeaders headers2 = new ClientHeaders();
        headers2.setTrace(new ClientTrace());
        assertNotEquals(headers1, headers2);
    }

    @Test
    public void equals_notEqual() {
        ClientHeaders headers1 = new ClientHeaders();
        headers1.setTrace(new ClientTrace());
        ClientHeaders headers2 = new ClientHeaders();
        headers2.setRouting(new ClientRouting());
        assertNotEquals(headers1, headers2);
    }

    @Test
    public void equals_bothEqual() {
        ClientHeaders headers1 = new ClientHeaders();
        ClientTrace trace = new ClientTrace();
        ClientRouting routing = new ClientRouting();
        headers1.setTrace(trace);
        headers1.setRouting(routing);
        ClientHeaders headers2 = new ClientHeaders();
        headers2.setTrace(trace);
        headers2.setRouting(routing);
        assertEquals(headers1, headers2);
    }

    @Test
    public void hashCode_clean() {
        new ClientHeaders().hashCode();
    }
}
