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

import io.americanexpress.synapse.utilities.common.number.DoubleUtils;

/**
 * CurrencyUtils class convert any amount into the correct currency.
 *
 * @author Christie Goldstein
 */
public class CurrencyUtils {

    /**
     * Default constructor creates a new instance of CurrencyUtils with default values
     */
    private CurrencyUtils() {
    }

    /**
     * Converts value from to be in correct currency when it is a Double.
     *
     * @param value        value needed to be converted
     * @param exchangeRate exchange rate from the backend API
     */
    public static Double convertCurrencyForDouble(Double value, Double exchangeRate) {
        Double convertedValue = null;
        if (value != null) {
            convertedValue = value * exchangeRate;
        }
        return convertedValue;
    }

    /**
     * Converts value from to be in correct currency when it is a String.
     *
     * @param value        value needed to be converted
     * @param exchangeRate exchange rate from the backend API
     */
    public static String convertCurrencyForString(String value, Double exchangeRate) {
        Double numericValue = DoubleUtils.tryParseDouble(value);
        numericValue = convertCurrencyForDouble(numericValue, exchangeRate);
        return numericValue == null ? null : numericValue.toString();
    }
}
