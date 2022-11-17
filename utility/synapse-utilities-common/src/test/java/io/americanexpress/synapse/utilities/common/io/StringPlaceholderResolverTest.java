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
package io.americanexpress.synapse.utilities.common.io;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringPlaceholderResolverTest {

    private static final StringPlaceholderResolver STRING_PLACEHOLDER_RESOLVER = new StringPlaceholderResolver("sample-placeholder-text.txt");

    private static final String SAMPLE_PLACEHOLDER_TEXT = "My first name is ${firstName} and my last name is ${lastName}.";

    @Test
    void resolve_givenNullProperties_expectedIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> STRING_PLACEHOLDER_RESOLVER.resolve(null));
    }

    @Test
    void resolve_givenEmptyProperties_expectedUnresolvedText() {
        String actual = STRING_PLACEHOLDER_RESOLVER.resolve(new Properties());
        assertEquals(SAMPLE_PLACEHOLDER_TEXT, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void resolve_givenPropertiesContainsUnknownKey_expectedUnresolvedText() {
        Properties properties = new Properties();
        properties.put("unknown", "a");
        String actual = STRING_PLACEHOLDER_RESOLVER.resolve(properties);
        assertEquals(SAMPLE_PLACEHOLDER_TEXT, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void resolve_givenPropertiesContainsPartialKeys_expectedPartiallyResolvedText() {
        Properties properties = new Properties();
        properties.put("firstName", "John");
        String expected = "My first name is John and my last name is ${lastName}.";
        String actual = STRING_PLACEHOLDER_RESOLVER.resolve(properties);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void resolve_givenPropertiesContainsAllKeys_expectedResolvedText() {
        Properties properties = new Properties();
        properties.put("firstName", "John");
        properties.put("lastName", "Doe");
        String expected = "My first name is John and my last name is Doe.";
        String actual = STRING_PLACEHOLDER_RESOLVER.resolve(properties);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}