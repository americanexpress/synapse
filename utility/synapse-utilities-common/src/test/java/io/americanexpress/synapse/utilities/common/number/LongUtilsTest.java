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
package io.americanexpress.synapse.utilities.common.number;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code LongUtilsTest} tests the {@link LongUtils}.
 */
class LongUtilsTest {

    @Test
    void tryParseLong_givenNull_expectedNull() {
        Long actual = LongUtils.tryParseLong(null);
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given1234_expected1234() {
        Long actual = LongUtils.tryParseLong("1234");
        Long expected = 1234L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenABC_expectedNull() {
        Long actual = LongUtils.tryParseLong("ABC");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenABC123_expectedNull() {
        Long actual = LongUtils.tryParseLong("ABC123");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given12345646WithDecimals_expectedNull() {
        Long actual = LongUtils.tryParseLong("12345646675.00000");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenNegative123_expectedNegative123() {
        Long actual = LongUtils.tryParseLong("-123");
        Long expected = -123L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given000_expected000() {
        Long actual = LongUtils.tryParseLong("000");
        Long expected = 000L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given0_expected0() {
        Long actual = LongUtils.tryParseLong("", 0L);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenInvalidString_expectedDefaultValue0() {
        Long actual = LongUtils.tryParseLong("invalid", 0L);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenNumericString23_expected23() {
        Long actual = LongUtils.tryParseLong("23", 0L);
        Long expected = 23L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void getValue_givenNull_expected0() {
        Long actual = LongUtils.getValue(null);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void getValue_givenValidLong23_expected23() {
        Long actual = LongUtils.getValue(23L);
        Long expected = 23L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}
