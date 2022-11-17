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

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * {@code IntegerUtils} class used to convert the string to integer if the value is valid.
 */
public class IntegerUtils {

    private static final XLogger logger = XLoggerFactory.getXLogger(IntegerUtils.class);

    private IntegerUtils() {
        //Private constructor to prevent instantiation of utility class.
    }

    /**
     * Try to parse the numerical string representation into its corresponding integer value.
     *
     * @param text containing the numerical string representation
     * @return the corresponding integer value if the parsing was successful; null otherwise
     */
    public static Integer tryParseInt(String text) {
        Integer value = null;
        // Try to get the numerical value from the string
        if (text != null) {
            try {
                value = Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                logger.warn("There was an error trying to parse " + text, e);
                value = null;
            }
        }
        return value;
    }


    /**
     * Try to parse the numerical string representation into its corresponding integer value.
     * If the parsing failed, return the specified default value.
     *
     * @param text         containing the numerical string representation
     * @param defaultValue the default value if the parsing failed
     * @return the corresponding integer value if the parsing was successful; otherwise return the defaultValue
     */
    public static Integer tryParseInt(String text, Integer defaultValue) {
        Integer value = tryParseInt(text);
        return value == null ? defaultValue : value;
    }

    /**
     * Get the value of this integer.
     *
     * @param number to get the value
     * @return the value of number or 0 if this number is null
     */
    public static Integer getValue(Integer number) {
        Integer value = 0;
        if (number != null) {
            value = number;
        }
        return value;
    }
}
