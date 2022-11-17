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
 * {@code DoubleUtilsTest} tests the {@link DoubleUtils}.
 */
class DoubleUtilsTest {

    private static final String VALUE_NOT_EQUAL = "The values are not equal.";

    private static final String VALUE_EQUAL = "The values are equal.";

    @Test
    void tryParseDouble_null() {
        Double actual = DoubleUtils.tryParseDouble(null);
        Double expected = null;
        assertEquals(expected, actual, "null");
    }

    @Test
    void tryParseDouble_123() {
        Double actual = DoubleUtils.tryParseDouble("123");
        Double expected = 123.0;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseDouble_space123() {
        Double actual = DoubleUtils.tryParseDouble(" 123 ");
        Double expected = 123.0;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseDouble_12340123() {
        Double actual = DoubleUtils.tryParseDouble("1234.0123");
        Double expected = 1234.0123;
        assertEquals(expected, actual, VALUE_EQUAL);
    }

    @Test
    void tryParseDouble_ABC() {
        Double actual = DoubleUtils.tryParseDouble("ABC");
        Double expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseDouble_neg1234() {
        Double actual = DoubleUtils.tryParseDouble("-1234");
        Double expected = -1234.0;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseDouble_0000() {
        Double actual = DoubleUtils.tryParseDouble("0000");
        Double expected = 0000000.00;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseDouble_00ABC() {
        Double actual = DoubleUtils.tryParseDouble("00ABC");
        Double expected = null;
        assertEquals(expected, actual, VALUE_NOT_EQUAL);
    }
}
