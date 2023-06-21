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
package io.americanexpress.synapse.utilities.common.currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@code CurrencyUtilsTest} tests the {@link CurrencyUtils}.
 */
class CurrencyUtilsTest {

    @Test
    void currencyUtils_doubleNull() {
        Double actual = CurrencyUtils.convertCurrencyForDouble(10.0, 1.5);
        Double expected = 15.0;
        assertEquals(expected, actual);
    }

    @Test
    void currencyUtils_doubleSuccess() {
        Double actual = CurrencyUtils.convertCurrencyForDouble(null, 1.5);
        assertNull(actual);
    }

    @Test
    void currencyUtils_stringNull() {
        String actual = CurrencyUtils.convertCurrencyForString("10.0", 1.5);
        String expected = "15.0";
        assertEquals(expected, actual);
    }

    @Test
    void currencyUtils_stringSuccess() {
        String actual = CurrencyUtils.convertCurrencyForString(null, 1.5);
        assertNull(actual);
    }

}
