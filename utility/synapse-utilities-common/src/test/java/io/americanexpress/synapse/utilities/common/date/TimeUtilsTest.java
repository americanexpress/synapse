package io.americanexpress.synapse.utilities.common.date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code TimeUtilsTest} tests the {@link TimeUtils}
 */
class TimeUtilsTest {

    // TIME
    public final static String ISO_8601_TIME = "16:19:01";
    public final static String HHMMSS_TIME = "161901";

    // FRACTION OF A SECOND: MILLISECONDS, MICROSECONDS, NANOSECONDS
    public final static String ONE = "1";
    public final static String MILLISECONDS1 = "." + ONE;
    public final static String MILLISECONDS2 = MILLISECONDS1 + ONE;
    public final static String MILLISECONDS3 = MILLISECONDS2 + ONE;
    public final static String MICROSECONDS1 = MILLISECONDS3 + ONE;
    public final static String MICROSECONDS2 = MICROSECONDS1 + ONE;
    public final static String MICROSECONDS3 = MICROSECONDS2 + ONE;
    public final static String NANOSECONDS1 = MICROSECONDS3 + ONE;
    public final static String NANOSECONDS2 = NANOSECONDS1 + ONE;
    public final static String NANOSECONDS3 = NANOSECONDS2 + ONE;

    // TIME WITH MILLISECONDS
    public final static String ISO_8601_TIME_MILLI1 = ISO_8601_TIME + MILLISECONDS1;
    public final static String ISO_8601_TIME_MILLI2 = ISO_8601_TIME + MILLISECONDS2;
    public final static String ISO_8601_TIME_MILLI3 = ISO_8601_TIME + MILLISECONDS3;

    // TIME WITH MICROSECONDS
    public final static String ISO_8601_TIME_MICRO1 = ISO_8601_TIME + MICROSECONDS1;
    public final static String ISO_8601_TIME_MICRO2 = ISO_8601_TIME + MICROSECONDS2;
    public final static String ISO_8601_TIME_MICRO3 = ISO_8601_TIME + MICROSECONDS3;

    // TIME WITH NANOSECONDS
    public final static String ISO_8601_TIME_NANO1 = ISO_8601_TIME + NANOSECONDS1;
    public final static String ISO_8601_TIME_NANO2 = ISO_8601_TIME + NANOSECONDS2;
    public final static String ISO_8601_TIME_NANO3 = ISO_8601_TIME + NANOSECONDS3;

    // INVALID TIME
    public final static String INVALID_ISO_8601_TIME = "24:19:01";
    public final static String INVALID_HHMMSS_TIME = "241901";
    public final static String INVALID_ISO_8601_TIME_FRACTION_OF_SECOND = ISO_8601_TIME_NANO3 + ONE;

    /**
     * Test whether the times are in ISO 8601 time format {@code HH:mm:ss.SSSSSSSSS};
     * where the fraction of seconds is optional.
     *
     * @param time the time
     * @see java.time.LocalTime
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_TIME, ISO_8601_TIME_MILLI1, ISO_8601_TIME_MILLI2, ISO_8601_TIME_MILLI3,
            ISO_8601_TIME_MICRO1, ISO_8601_TIME_MICRO2, ISO_8601_TIME_MICRO3,
            ISO_8601_TIME_NANO1, ISO_8601_TIME_NANO2, ISO_8601_TIME_NANO3})
    void isIso8601Time_givenValidIso8601Time_expectTrue(String time) {
        assertTrue(TimeUtils.isIso8601Time(time));
    }

    /**
     * Test whether the times are not in ISO 8601 time format {@code HH:mm:ss.SSSSSSSSS};
     * where the fraction of seconds is optional.
     * Null and empty values are also tested.
     *
     * @param time the time
     * @see java.time.LocalTime
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {HHMMSS_TIME, INVALID_ISO_8601_TIME, INVALID_ISO_8601_TIME_FRACTION_OF_SECOND})
    void isIso8601Time_givenInvalidIso8601Time_expectFalse(String time) {
        assertFalse(TimeUtils.isIso8601Time(time));
    }

    /**
     * Test whether the time values are in HHmmss format.
     */
    @Test
    void isValidHhmmssTime_givenValidHhmmssTime_expectTrue() {
        assertTrue(TimeUtils.isHhmmssTime(HHMMSS_TIME));
    }

    /**
     * Test whether the time values are in HHmmss format.
     * Null and empty values are also tested.
     *
     * @param time the time
     * @see java.time.LocalTime
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_HHMMSS_TIME, ISO_8601_TIME})
    void isValidHhmmssTime_givenInvalidHhmmssTime_expectFalse(String time) {
        assertFalse(TimeUtils.isHhmmssTime(time));
    }

}

