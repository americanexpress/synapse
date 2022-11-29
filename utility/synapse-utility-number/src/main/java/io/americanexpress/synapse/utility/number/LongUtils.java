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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code LongUtils} class used to convert the string to long if the value is valid.
 */
public class LongUtils {

    /**
     * Logger.
     */
    private static final XLogger logger = XLoggerFactory.getXLogger(LongUtils.class);

    private LongUtils() {
        //Private constructor to prevent instantiation of utility class.
    }

    /**
     * Regex pattern used to detect longs.
     */
    private static final Pattern LONG_PATTERN = Pattern.compile("-??\\d+");

    /**
     * Try to parse the numerical string representation into its corresponding long value.
     *
     * @param text containing the numerical string representation
     * @return the corresponding long value if the parsing was successful; null otherwise
     */
    public static Long tryParseLong(String text) {
        Long value = null;
        if(StringUtils.isNotBlank(text)) {
            text = text.trim();
            Matcher matcher = LONG_PATTERN.matcher(text);
            if(matcher.matches()) {
                value = Long.valueOf(text);
            }
        }

        if(value == null) {

            // unable to parse
            logger.warn("There was an error trying to parse {0}", text);
        }
        return value;
    }


    /**
     * Try to parse the numerical string representation into its corresponding long value.
     * If the parsing failed, return the specified default value.
     *
     * @param text         containing the numerical string representation
     * @param defaultValue the default value if the parsing failed
     * @return the corresponding long value if the parsing was successful; otherwise return the defaultValue
     */
    public static Long tryParseLong(String text, Long defaultValue) {
        Long value = tryParseLong(text);
        return value == null ? defaultValue : value;
    }

    /**
     * Get the value of this long.
     *
     * @param number to get the value
     * @return the value of number or 0 if this number is null
     */
    public static Long getValue(Long number) {
        Long value = 0L;
        if (number != null) {
            value = number;
        }
        return value;
    }

}
