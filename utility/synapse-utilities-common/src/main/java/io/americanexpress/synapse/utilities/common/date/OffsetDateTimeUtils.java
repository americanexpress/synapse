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

import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.regex.Pattern;

import static io.americanexpress.synapse.utilities.common.date.DateTimeUtils.ISO_8601_DATE_TIME_LITERAL_T;

/**
 * {@code OffsetDateTimeUtils} provides methods for offset date conversions and validations.
 */
public class OffsetDateTimeUtils {

    /**
     * UTC offset.
     */
    public static final String UTC_OFFSET = "+00:00";

    /**
     * UTC literal Z.
     */
    public static final String UTC_LITERAL_Z = "Z";

    /**
     * The compiled regular expression for an ISO 8601 offset date-time format.
     */
    public static final Pattern ISO8601_OFFSET_DATE_TIME_PATTERN = Pattern.compile("^(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)T(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:\\.\\d{1,9})?(?:Z|[+-][01](?:[0-7]:[0-5]\\d|8:00))$");

    /**
     * The compiled regular expression for an ISO 8601 offset format.
     */
    public static final Pattern ISO8601_OFFSET_PATTERN = Pattern.compile("^(?:Z|[+-][01](?:[0-7]:[0-5]\\d|8:00))$");

    private OffsetDateTimeUtils() {
        //Private constructor to prevent instantiation of utils class.
    }

    /**
     * Check if the value matches the ISO 8601 standard for date and time with offset format.
     * For example, 2021-09-23T14:42:44.571777403-04:00 is valid.
     * Values should be formatted as {@code YYYY-MM-DDTHH:mm:ss.SSSSSSSSS+hh:mm};
     * where the fraction of a second is optional and is precise up to nanoseconds
     * and with {@code Z} or +/- the offset from UTC.
     * <p>
     * Valid values can be parsed by calling {@link OffsetDateTime#parse(CharSequence)}.
     * <p>
     * For more details on ISO 8601 standard see https://www.w3.org/TR/NOTE-datetime
     *
     * @param offsetDateTime the offset date time
     * @return true if valid
     */
    public static boolean isIso8601OffsetDateTime(String offsetDateTime) {
        return StringUtils.isNotBlank(offsetDateTime) && ISO8601_OFFSET_DATE_TIME_PATTERN.matcher(offsetDateTime).matches();
    }

    /**
     * Check if the value matches the ISO 8601 standard for offset.
     * For example, {@code +05:00}, {@code -05:00}, or {@code Z} for UTC.
     * <p>
     * Valid values can be parsed by calling {@link ZoneOffset#of(String)}.
     * The global offset range was extended to -12:00 to +14:00 in 2012.
     * The JDK future-proof {@link ZoneOffset} by extending the range to -18:00 to 18:00 inclusive.
     * <p>
     * For more details on ISO 8601 standard see https://www.w3.org/TR/NOTE-datetime
     *
     * @param offset the offset
     * @return true if valid
     */
    public static boolean isIso8601Offset(String offset) {
        return StringUtils.isNotBlank(offset) && ISO8601_OFFSET_PATTERN.matcher(offset).matches();
    }

    /**
     * Convert an ISO 8601 date-time value to an ISO 8601 offset date time with UTC offset.
     *
     * @param dateTimeValue the date time value
     * @return the formatted offset date time
     * @see OffsetDateTime
     */
    public static String convertToIso8601OffsetDateTime(String dateTimeValue) {
        return convertToIso8601OffsetDateTime(dateTimeValue, UTC_OFFSET);
    }

    /**
     * Convert an ISO 8601 date-time value to an ISO 8601 offset date time using the offset value.
     * The same value is returned if it is an ISO 8601 offset date time, or if the value or offset is not in a valid ISO 8601 format.
     *
     * @param dateTimeValue the date time value
     * @param offset        the offset
     * @return the formatted offset date time
     * @see OffsetDateTime
     */
    public static String convertToIso8601OffsetDateTime(String dateTimeValue, String offset) {
        String formattedOffsetDateTime = StringUtils.indexOf(dateTimeValue, StringUtils.SPACE) == 10 ? StringUtils.replaceOnce(dateTimeValue, StringUtils.SPACE, ISO_8601_DATE_TIME_LITERAL_T) : dateTimeValue;

        if ((DateUtils.isIso8601Date(formattedOffsetDateTime) || DateTimeUtils.isIso8601DateTime(formattedOffsetDateTime)) && isIso8601Offset(offset)) {
            formattedOffsetDateTime = DateTimeUtils.convertToIso8601DateTime(formattedOffsetDateTime) + offset;
        }

        return formattedOffsetDateTime;
    }

}
