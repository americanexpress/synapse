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
package io.americanexpress.synapse.client.rest.client;

import org.junit.jupiter.api.Test;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.StringUtils;

class PathVariableUriCreatorTest {

    @Test
    void create_givenNullPathVariables_expectedEmptyString() {
        String actual = PathVariableUriCreator.create();
        assertEquals(StringUtils.EMPTY, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void create_givenPathVariable_expectedPathVariable() {
        String pathVariable = "sample";
        String expected = "/" + pathVariable;
        String actual = PathVariableUriCreator.create(pathVariable);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void create_givenMultiplePathVariables_expectedMultiplePathVariables() {
    	String pathVariable1 = "first";
    	String pathVariable2 = "second";
        String expected = "/" + pathVariable1 + "/" + pathVariable2;
        String actual = PathVariableUriCreator.create(pathVariable1, pathVariable2);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}
