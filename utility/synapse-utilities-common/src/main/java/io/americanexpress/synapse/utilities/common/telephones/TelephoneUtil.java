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
package io.americanexpress.synapse.utilities.common.telephones;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * {@code TelephoneUtil} class is used to perform utility operations for telephone numbers such as validation.
 *
 * @author Paolo Claudio
 */
public final class TelephoneUtil {

    /**
     * Pattern used to check if the input text matches the possible International Numbering Plan
     * which is defined as minimally 7 digits and at maximum 15 digits.
     */
    private static final Pattern INTERNATIONAL_NUMBERING_PLAN_PATTERN = Pattern.compile("\\d{7,15}");

    /**
     * Default constructor creates a new instance of TelephoneUtil with default values.
     */
    private TelephoneUtil() {

        // A utility class with only static members must have a private constructor
    }

    /**
     * Check to see if this raw text is a possible telephone number for any of the world's telephone number formats,
     * including the United States of America and any national or international telephone number format.
     *
     * @param text
     * @return true if this telephone number is a possible telephone number; false otherwise
     */
    public static boolean isPossibleTelephoneNumber(String text) {

        if (StringUtils.isBlank(text)) {
            return false;
        }

        // Remove all non-digit characters from this string, including +, (, ), - and white spaces
        String telephoneNumber = text.replaceAll("\\D", "");
        if (StringUtils.isBlank(telephoneNumber)) {
            return false;
        }

        // According to the International Numbering Plan, telephone numbers worldwide are
        // minimally 7 digits and a maximum of 15 digits
        return INTERNATIONAL_NUMBERING_PLAN_PATTERN.matcher(telephoneNumber).matches();
    }

    /**
     * Check to see if this telephone number is a possible North American telephone number.
     * A North American telephone number is of the format:
     * 1XXXXXXXXXX or
     * XXXXXXXXXX
     *
     * @param telephoneNumber containing the telephone number
     * @return true if this telephone number is a possible North American telephone number; false otherwise
     */
    public static boolean isPossibleNorthAmericanTelephoneNumber(String telephoneNumber) {
        int telephoneNumberLength = telephoneNumber.length();
        return telephoneNumberLength == 10 || (telephoneNumberLength == 11 && telephoneNumber.startsWith("1"));
    }

    /**
     * Check to see if this raw text is a possible country code. All of the country codes
     * in the world are between 1 to 3 digits.
     *
     * @param text to be checked
     * @return true if and only if this text is a possible country code; false otherwise
     */
    public static boolean isPossibleCountryCode(String text) {
        return StringUtils.isNotBlank(text) && text.replaceAll("\\D", "").matches("\\d{1,3}");
    }

    /**
     * Removes all non-digit characters from this string, including +, (, ), - and blank spaces
     *
     * @param rawTelephoneLineNumber the formatted Telephone line number
     * @return the Non Formatter Telephone line number
     */
    public static String getNonFormattedTelephoneLineNumber(String rawTelephoneLineNumber) {
        return StringUtils.isNotBlank(rawTelephoneLineNumber) ? rawTelephoneLineNumber.replaceAll("\\D", "") : rawTelephoneLineNumber;
    }
}
