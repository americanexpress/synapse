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
package com.americanexpress.synapse.client.rest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientRoutingTest {

    @Test
    public void clientRouting_constructor() {
        new ClientRouting();
    }

    @Test
    public void equals_this() {
        ClientRouting routing1 = new ClientRouting();
        ClientRouting routing2 = routing1;
        assertEquals(routing1, routing2);
    }

    @Test
    public void equals_null() {
        ClientRouting routing1 = new ClientRouting();
        ClientRouting routing2 = null;
        assertNotEquals(routing1, routing2);
    }

    @Test
    public void equals_invalidType() {
        ClientRouting routing1 = new ClientRouting();
        String routing2 = "";
        assertNotEquals(routing1, routing2);
    }


    @Test
    public void equals_bothEqual() {
        ClientRouting routing1 = new ClientRouting();
        ClientRouting routing2 = new ClientRouting();
        assertEquals(routing1, routing2);
    }

    @Test
    public void hashCode_clean() {
        new ClientRouting().hashCode();
    }
}
