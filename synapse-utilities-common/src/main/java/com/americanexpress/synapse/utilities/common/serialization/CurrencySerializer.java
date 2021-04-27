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
package com.americanexpress.synapse.utilities.common.serialization;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * CurrencySerializer class serializes BigDecimal objects into a currency format.
 *
 * @author Paolo Claudio
 */
public class CurrencySerializer extends NumberSerializer<BigDecimal> {

    /**
     * Set the number format as the default locale's currency formatter.
     */
    @Override
    protected void setNumberFormat() {
        numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
    }

    /**
     * Serialize the currency.
     *
     * @param value to be formatted
     * @return the formatted number
     */
    @Override
    protected String serialize(BigDecimal value) {

        String formattedValue;

        // Some locales, including the US, format currencies of negative values
        // with parentheses.
        // Example: -1234.444 would get formatted into (1,234.44)
        // Update this behavior so that the value gets formatted into -$1,234.44 instead
        if (value.doubleValue() < 0) {
            value = value.multiply(BigDecimal.valueOf(-1));
            formattedValue = numberFormat.format(value);
            formattedValue = "-" + formattedValue;
        } else {
            formattedValue = numberFormat.format(value);
        }

        return formattedValue;
    }

    /**
     * Serialize the string representation of the currency.
     *
     * @param text to be converted into a BigDecimal
     * @return the serialized string representation of the currency
     */
    public String serialize(String text) {
        String serializedValue = text;
        if (StringUtils.isNotBlank(text)) {
            text = text.trim();
            BigDecimal bigDecimal;
            try {
                bigDecimal = new BigDecimal(text);
                serializedValue = serialize(bigDecimal);
            } catch (NumberFormatException numberFormatException) {
                logger.warn("An exception occurred while trying to create the BigDecimal from " + text, numberFormatException);
            }
        }
        return serializedValue;
    }
}
