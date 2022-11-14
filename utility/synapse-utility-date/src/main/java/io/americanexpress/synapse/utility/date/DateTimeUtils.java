package io.americanexpress.synapse.utility.date;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.regex.Pattern;

public class DateTimeUtils {

    /**
     * Literal for delimiter for ISO 8601 date time.
     */
    public static final String ISO_8601_DATE_TIME_LITERAL_T = "T";

    /**
     * Midnight in ISO 8601 time format.
     */
    public static final String MIDNIGHT_ISO_8601_TIME = "00:00:00.000";

    /**
     * The compiles regular expression for an ISO 8601 date-time format.
     */
    public static final Pattern ISO8601_DATE_TIME_PATTERN = Pattern.compile("^(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)T(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:\\.\\d{1,9})?$");

    /**
     * Check if the value matches the ISO 8601 standard for date and time format.
     * For example, 2021-09-23T14:42:44.571777403 is valid.
     * Values should be formatted as {@code YYYY-MM-DDTHH:mm:ss.SSSSSSSSS};
     * where the fraction of a second is optional and is precise up to nanoseconds.
     * <p>
     * Valid values can be parsed by calling {@link LocalDateTime#parse(CharSequence)}.
     * <p>
     * For more details on ISO 8601 standard see https://www.w3.org/TR/NOTE-datetime
     *
     * @param dateTime the date time
     * @return true if valid
     */
    public static boolean isIso8601DateTime(String dateTime) {
        return StringUtils.isNotBlank(dateTime) && ISO8601_DATE_TIME_PATTERN.matcher(dateTime).matches();
    }

    /**
     * Convert an ISO 8601 date-time value to an ISO 8601 date time with midnight as the time.
     *
     * @param dateTimeValue the date-time value
     * @return the formatted date time
     * @see LocalDate
     * @see LocalDateTime
     */
    public static String convertToIso8601DateTime(String dateTimeValue) {
        return convertToIso8601DateTime(dateTimeValue, MIDNIGHT_ISO_8601_TIME);
    }

    /**
     * Convert an ISO 8601 date-time value to an ISO 8601 date time using the offset value.
     * The same value is returned if it is an ISO 8601 date time, or if the value or time is not in a valid ISO 8601 format.
     *
     * @param dateTimeValue the date-time value
     * @param time          the time
     * @return the formatted date time
     * @see LocalDateTime
     */
    public static String convertToIso8601DateTime(String dateTimeValue, String time) {
        String formattedDateTime = dateTimeValue;

        // Format an ISO 8601 date to date time.
        if (DateUtils.isIso8601Date(formattedDateTime) && TimeUtils.isIso8601Time(time)) {
            formattedDateTime = formattedDateTime + ISO_8601_DATE_TIME_LITERAL_T + time;
        }

        return formattedDateTime;
    }

    /**
     * Check if the date time value is in ISO 8601 date time format.
     *
     * @param dateTimeValue the date time value
     * @return true if the valid
     * @see LocalDate
     * @see LocalDateTime
     * @see OffsetDateTime
     */
    public static boolean isIso8601DateTimeFormat(String dateTimeValue) {
        return DateUtils.isIso8601Date(dateTimeValue) || DateTimeUtils.isIso8601DateTime(dateTimeValue) || OffsetDateTimeUtils.isIso8601OffsetDateTime(dateTimeValue);
    }

}
