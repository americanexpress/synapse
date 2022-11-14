package io.americanexpress.synapse.utility.date;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class TimeUtils {

    /**
     * The constant AMERICA_PHOENIX_ZONE_ID.
     */
    public static final ZoneId AMERICA_PHOENIX_ZONE_ID = ZoneId.of("America/Phoenix");

    /**
     * The compiles regular expression for an ISO 8601 time format.
     */
    public static final Pattern ISO8601_TIME_PATTERN = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d(?:\\.\\d{1,9})?$");

    /**
     * Check if the value matches the ISO 8601 standard for date and time format.
     * For example, 14:42:44.571777403 is valid.
     * Values should be formatted as {@code HH:mm:ss.SSSSSSSSS};
     * where the fraction of a second is optional and is precise up to nanoseconds.
     * <p>
     * Valid values can be parsed by calling {@link LocalTime#parse(CharSequence)}.
     * <p>
     * For more details on ISO 8601 standard see https://www.w3.org/TR/NOTE-datetime
     *
     * @param time the time
     * @return the boolean
     */
    public static boolean isIso8601Time(String time) {
        return StringUtils.isNotBlank(time) && ISO8601_TIME_PATTERN.matcher(time).matches();
    }

    /**
     * Check if the value is a valid time in HHmmss format.
     *
     * @param time the time
     * @return true if valid date
     */
    public static boolean isHhmmssTime(String time) {
        String formattedTime = time;
        boolean isValid = false;

        // Convert the date to ISO 8601 format.
        if (StringUtils.isNumeric(time) && formattedTime.length() == 6) {
            formattedTime = new StringJoiner(":").add(formattedTime.substring(0, 2)).add(formattedTime.substring(2, 4)).add(formattedTime.substring(4, 6)).toString();
            isValid = isIso8601Time(formattedTime);
        }

        return isValid;
    }

}
