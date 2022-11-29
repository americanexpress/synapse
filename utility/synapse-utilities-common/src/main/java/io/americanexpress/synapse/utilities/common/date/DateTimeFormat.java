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
package io.americanexpress.synapse.utilities.common.date;

/**
 * {@code DateTimeFormat} represents type of date-time format.
 */
public enum DateTimeFormat {

    /**
     * Iso local date time sss date time format.
     */
    ISO_LOCAL_DATE_TIME_SSS("yyyy-MM-dd'T'HH:mm:ss.SSS", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$"),
    /**
     * Iso local date time ss date time format.
     */
    ISO_LOCAL_DATE_TIME_SS("yyyy-MM-dd'T'HH:mm:ss.SS", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{2}$"),
    /**
     * Iso local date time date time format.
     */
    ISO_LOCAL_DATE_TIME("yyyy-MM-dd'T'HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$"),
    /**
     * The Iso date time sss.
     */
    ISO_DATE_TIME_SSS("yyyy-MM-dd HH:mm:ss.SSS", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}$"),
    /**
     * The Iso date time ss.
     */
    ISO_DATE_TIME_SS("yyyy-MM-dd HH:mm:ss.SS", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{2}$"),
    /**
     * The Iso date time.
     */
    ISO_DATE_TIME("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"),
    /**
     * Iso instant date time format.
     */
    ISO_INSTANT("yyyy-MM-dd'T'HH:mm:ssZ", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$"),
    /**
     * The Iso date time s.
     */
    ISO_DATE_TIME_S("yyyy-MM-dd HH:mm:ss.S", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d$");

    private final String value;

    private final String regularExpression;

    DateTimeFormat(String value, String regularExpression) {
        this.value = value;
        this.regularExpression = regularExpression;
    }

    /**
     * Returns date format of the ISO value selected in the ENUM.
     *
     * @return string of the ISO date format
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the regular expression related the ISO date.
     *
     * @return string with the regular expression
     */
    public String getRegularExpression() {
        return regularExpression;
    }
}
