package io.americanexpress.synapse.utility.date;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static io.americanexpress.synapse.utility.date.DateTimeUtils.ISO_8601_DATE_TIME_LITERAL_T;
import static io.americanexpress.synapse.utility.date.DateTimeUtils.MIDNIGHT_ISO_8601_TIME;
import static io.americanexpress.synapse.utility.date.DateUtilsTest.*;
import static io.americanexpress.synapse.utility.date.OffsetDateTimeUtilsTest.*;
import static io.americanexpress.synapse.utility.date.TimeUtilsTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DateTimeUtilsTest {

    // DATE TIMES
    public final static String ISO_8601_DATE_SPACE_ISO_8601_TIME = ISO_8601_DATE + StringUtils.SPACE + ISO_8601_TIME;
    public final static String ISO_8601_DATE_TIME_MIDNIGHT = ISO_8601_DATE + ISO_8601_DATE_TIME_LITERAL_T + MIDNIGHT_ISO_8601_TIME;
    public final static String ISO_8601_DATE_TIME = ISO_8601_DATE + ISO_8601_DATE_TIME_LITERAL_T + ISO_8601_TIME;
    public final static String ISO_8601_DATE_TIME_MILLI1 = ISO_8601_DATE_TIME + MILLISECONDS1;
    public final static String ISO_8601_DATE_TIME_MILLI2 = ISO_8601_DATE_TIME + MILLISECONDS2;
    public final static String ISO_8601_DATE_TIME_MILLI3 = ISO_8601_DATE_TIME + MILLISECONDS3;
    public final static String ISO_8601_DATE_TIME_MICRO1 = ISO_8601_DATE_TIME + MICROSECONDS1;
    public final static String ISO_8601_DATE_TIME_MICRO2 = ISO_8601_DATE_TIME + MICROSECONDS2;
    public final static String ISO_8601_DATE_TIME_MICRO3 = ISO_8601_DATE_TIME + MICROSECONDS3;
    public final static String ISO_8601_DATE_TIME_NANO1 = ISO_8601_DATE_TIME + NANOSECONDS1;
    public final static String ISO_8601_DATE_TIME_NANO2 = ISO_8601_DATE_TIME + NANOSECONDS2;
    public final static String ISO_8601_DATE_TIME_NANO3 = ISO_8601_DATE_TIME + NANOSECONDS3;

    // INVALID DATE TIMES
    public final static String INVALID_ISO_8601_DATE_TIME_FRACTION_OF_SECOND = ISO_8601_DATE_TIME + ONE;

    /**
     * Test whether the date times are in ISO 8601 date time format {@code YYYY-MM-DDTHH:mm:ss.SSSSSSSSS};
     * where the fraction of seconds is optional.
     *
     * @param dateTime the date time
     * @see LocalDateTime
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_DATE_TIME, ISO_8601_DATE_TIME_MILLI1, ISO_8601_DATE_TIME_MILLI2, ISO_8601_DATE_TIME_MILLI3,
            ISO_8601_DATE_TIME_MICRO1, ISO_8601_DATE_TIME_MICRO2, ISO_8601_DATE_TIME_MICRO3,
            ISO_8601_DATE_TIME_NANO1, ISO_8601_DATE_TIME_NANO2, ISO_8601_DATE_TIME_NANO3})
    void isIso8601DateTime_givenValidIso8601DateTime_expectTrue(String dateTime) {
        assertTrue(DateTimeUtils.isIso8601DateTime(dateTime));
    }

    /**
     * Test whether the date times are not in ISO 8601 date time format {@code YYYY-MM-DDTHH:mm:ss.SSSSSSSSS};
     * where the fraction of seconds is optional.
     * Null and empty values are also tested.
     *
     * @param dateTime the date time
     * @see LocalDateTime
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {ISO_8601_DATE_SPACE_ISO_8601_TIME, INVALID_ISO_8601_DATE_TIME_FRACTION_OF_SECOND})
    void isIso8601DateTime_givenInvalidIso8601DateTime_expectFalse(String dateTime) {
        assertFalse(DateTimeUtils.isIso8601DateTime(dateTime));
    }

    /**
     * Test the conversion of an ISO 8601 date to an ISO 8601 date time with midnight as the default time.
     */
    @Test
    void convertToIso8601DateTime_givenIso8601Date_expectIso8601DateTime() {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(ISO_8601_DATE);
        assertEquals(ISO_8601_DATE_TIME_MIDNIGHT, iso8601DateTime);
    }

    /**
     * Test the conversion of a non ISO 8601 date to ISO 8601 date time.
     * Null and empty values are also tested.
     *
     * @param date the date
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, ISO_8601_DATE_TIME})
    void convertToIso8601DateTime_givenInvalidIso8601DateOrValidIso8601DateTime_expectSameValue(String date) {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(date);
        assertEquals(date, iso8601DateTime);
    }

    /**
     * Test the conversion of an ISO 8601 date to an ISO 8601 date time.
     */
    @Test
    void convertToIso8601DateTime_givenValidIso8601DateAndValidIso8601Time_expectIso8601DateTime() {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(ISO_8601_DATE, ISO_8601_TIME);
        assertEquals(ISO_8601_DATE_TIME, iso8601DateTime);
    }

    /**
     * Test the conversion of an ISO 8601 date to an ISO 8601 date time.
     */
    @Test
    void convertToIso8601DateTime_givenInvalidIso8601DateAndInvalidIso8601Time_expectSameInvalidIso8601Date() {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(INVALID_ISO_8601_DATE, INVALID_ISO_8601_TIME);
        assertEquals(INVALID_ISO_8601_DATE, iso8601DateTime);
    }

    /**
     * Test the conversion of a non ISO 8601 date to ISO 8601 date time.
     * Null and empty values are also tested.
     *
     * @param dateTimeValue the date-time value
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, YYYYMMDD_DATE, ISO_8601_DATE_SPACE_ISO_8601_TIME, INVALID_ISO_8601_DATE_TIME_FRACTION_OF_SECOND})
    void convertToIso8601DateTime_givenInvalidIso8601DateTimeValuesAndValidIso8601Time_expectSameInvalidIso8601Date(String dateTimeValue) {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(dateTimeValue, ISO_8601_TIME);
        assertEquals(dateTimeValue, iso8601DateTime);
    }

    /**
     * Test the conversion of a valid ISO 8601 date and invalid ISO 8601 time to ISO 8601 date time.
     * Null and empty values are also tested.
     *
     * @param time the time
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_TIME, INVALID_ISO_8601_TIME_FRACTION_OF_SECOND})
    void convertToIso8601DateTime_givenValidIso8601DateAndInvalidIso8601Time_expectValidIso8601Date(String time) {
        String iso8601DateTime = DateTimeUtils.convertToIso8601DateTime(ISO_8601_DATE, time);
        assertEquals(ISO_8601_DATE, iso8601DateTime);
    }

    /**
     * Test if the date-time values are in ISO 8601 format.
     *
     * @param dateTimeValue the date-time value
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_DATE, ISO_8601_DATE_TIME,
            ISO_8601_DATE_TIME_MILLI1, ISO_8601_DATE_TIME_MILLI2, ISO_8601_DATE_TIME_MILLI3,
            ISO_8601_DATE_TIME_MICRO1, ISO_8601_DATE_TIME_MICRO2, ISO_8601_DATE_TIME_MICRO3,
            ISO_8601_DATE_TIME_NANO1, ISO_8601_DATE_TIME_NANO2, ISO_8601_DATE_TIME_NANO3,
            ISO_8601_OFFSET_DATE_TIME, ISO_8601_OFFSET_DATE_TIME_PLUS, ISO_8601_OFFSET_DATE_TIME_MINUS,
            ISO_8601_OFFSET_DATE_TIME_MILLI1, ISO_8601_OFFSET_DATE_TIME_MILLI2, ISO_8601_OFFSET_DATE_TIME_MILLI3,
            ISO_8601_OFFSET_DATE_TIME_MICRO1, ISO_8601_OFFSET_DATE_TIME_MICRO2, ISO_8601_OFFSET_DATE_TIME_MICRO3,
            ISO_8601_OFFSET_DATE_TIME_NANO1, ISO_8601_OFFSET_DATE_TIME_NANO2, ISO_8601_OFFSET_DATE_TIME_NANO3})
    void isIso8601DateTimeFormat_givenValidIso8601DateTimeValues_expectTrue(String dateTimeValue) {
        assertTrue(DateTimeUtils.isIso8601DateTimeFormat(dateTimeValue));
    }

    /**
     * Test if the date time values are in ISO 8601 format.
     * Null and empty values are also tested.
     *
     * @param dateTimeValue the date-time value
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, YYYYMMDD_DATE,
            ISO_8601_DATE_SPACE_ISO_8601_TIME, INVALID_ISO_8601_DATE_TIME_FRACTION_OF_SECOND,
            INVALID_ISO_8601_OFFSET_DATE_TIME, INVALID_ISO_8601_OFFSET_DATE_TIME_FORMAT, INVALID_ISO_8601_OFFSET_DATE_TIME_FRACTION_OF_SECOND})
    void isIso8601DateTimeFormat_givenInvalidIso8601DateTimeValues_expectFalse(String dateTimeValue) {
        assertFalse(DateTimeUtils.isIso8601DateTimeFormat(dateTimeValue));
    }

}
