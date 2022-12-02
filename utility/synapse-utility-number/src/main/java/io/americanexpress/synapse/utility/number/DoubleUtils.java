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

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * {@code DoubleUtils} class used to convert the string to double if the value is valid.
 */
public class DoubleUtils {

    private static final XLogger logger = XLoggerFactory.getXLogger(DoubleUtils.class);

    private DoubleUtils() {
        //Private constructor to prevent instantiation of utility class.
    }

    /**
     * Try to parse the numerical string representation into its corresponding double value.
     *
     * @param text containing the numerical string representation
     * @return the corresponding double value if the parsing was successful; null otherwise
     */
    public static Double tryParseDouble(String text) {
        Double value = null;
        // Try to get the numerical value from the string
        if (text != null) {
            try {
                value = Double.parseDouble(text.trim());
            } catch (NumberFormatException e) {
                logger.catching(e);
                value = null;
            }
        }
        return value;
    }
}
