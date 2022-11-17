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

/**
 * {@code ClientTraceTest} tests the {@link ClientTrace}
 */
class ClientTraceTest {

    @Test
    void getCorrelationId_null() {
        ClientTrace trace = new ClientTrace();
        trace.getCorrelationId();
    }

    @Test
    void setCorrelationId_null() {
        ClientTrace trace = new ClientTrace();
        trace.setCorrelationId(null);
    }

    @Test
    void equals_this() {
        ClientTrace trace1 = new ClientTrace();
        ClientTrace trace2 = trace1;
        assertEquals(trace1, trace2);
    }

    @Test
    void equals_null() {
        ClientTrace trace1 = new ClientTrace();
        ClientTrace trace2 = null;
        assertNotEquals(trace1, trace2);
    }

    @Test
    void equals_invalidType() {
        ClientTrace trace1 = new ClientTrace();
        String trace2 = "";
        assertNotEquals(trace1, trace2);
    }

    @Test
    void equals_instanceContainsNull() {
        ClientTrace trace1 = new ClientTrace();
        ClientTrace trace2 = new ClientTrace();
        trace2.setCorrelationId("");
        assertNotEquals(trace1, trace2);
    }

    @Test
    void equals_notEqual() {
        ClientTrace trace1 = new ClientTrace();
        trace1.setCorrelationId("a");
        ClientTrace trace2 = new ClientTrace();
        trace2.setCorrelationId("b");
        assertNotEquals(trace1, trace2);
    }

    @Test
    void equals_bothEqual() {
        ClientTrace trace1 = new ClientTrace();
        trace1.setCorrelationId("");
        ClientTrace trace2 = new ClientTrace();
        trace2.setCorrelationId("");
        assertEquals(trace1, trace2);
    }

    @Test
    void hashCode_clean() {
        new ClientTrace().hashCode();
    }
}
