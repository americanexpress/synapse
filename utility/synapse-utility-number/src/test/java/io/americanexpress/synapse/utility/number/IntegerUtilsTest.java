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
package io.americanexpress.synapse.utility.number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code IntegerUtilsTest} tests the {@link IntegerUtils}.
 */
class IntegerUtilsTest {

    private static final String VALUE_NOT_EQUAL = "The values are not equal.";

    private static final String VALUE_EQUAL = "The values are equal.";

    @Test
    void tryParseInt_null() {
        Integer actual = IntegerUtils.tryParseInt(null);
        Integer expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseInt_1234() {
        Integer actual = IntegerUtils.tryParseInt("1234");
        Integer expected = 1234;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseInt_ABC() {
        Integer actual = IntegerUtils.tryParseInt("ABC");
        Integer expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseInt_ABC123() {
        Integer actual = IntegerUtils.tryParseInt("ABC123");
        Integer expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseInt_12345646WithDecimals() {
        Integer actual = IntegerUtils.tryParseInt("12345646675.00000");
        Integer expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseInt_neg123() {
        Integer actual = IntegerUtils.tryParseInt("-123");
        Integer expected = -123;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseInt_000() {
        Integer actual = IntegerUtils.tryParseInt("000");
        Integer expected = 000;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseInt_0() {
        Integer actual = IntegerUtils.tryParseInt("", 0);
        Integer expected = 0;
        assertEquals(expected, actual, VALUE_EQUAL);
    }
}
