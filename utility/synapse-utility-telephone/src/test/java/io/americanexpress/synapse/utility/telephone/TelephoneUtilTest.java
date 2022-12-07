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
package io.americanexpress.synapse.utility.telephone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code TelephoneUtilTest} tests the {@link TelephoneUtil}
 */
class TelephoneUtilTest {

    private static final String TELEPHONE_POSSIBLE = "Telephone number is possible.";

    private static final String TELEPHONE_NOT_POSSIBLE = "Telephone number is not possible.";

    @Test
    void isPossibleTelephoneNumber_null() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber(null);
        assertFalse(actual, TELEPHONE_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_empty() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("");
        assertFalse(actual, TELEPHONE_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_alpha() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("a");
        assertFalse(actual, TELEPHONE_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_alphanumeric() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("a0");
        assertFalse(actual, TELEPHONE_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_digitsUnknown() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("0");
        assertFalse(actual, TELEPHONE_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_northAmerica10Digits() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("9541111111");
        assertTrue(actual, TELEPHONE_NOT_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_northAmericaCountryCode() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("19541111111");
        assertTrue(actual, TELEPHONE_NOT_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_northAmericaFormatted10Digits() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("(954)111-1111");
        assertTrue(actual, TELEPHONE_NOT_POSSIBLE);
    }

    @Test
    void isPossibleTelephoneNumber_northAmericaFormattedCountryCode() {
        boolean actual = TelephoneUtil.isPossibleTelephoneNumber("+1(954)111-1111");
        assertTrue(actual, TELEPHONE_NOT_POSSIBLE);
    }
}
