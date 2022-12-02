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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static io.americanexpress.synapse.utilities.common.date.DateTimeUtilsTest.*;
import static io.americanexpress.synapse.utilities.common.date.DateUtilsTest.*;
import static io.americanexpress.synapse.utilities.common.date.OffsetDateTimeUtils.*;
import static io.americanexpress.synapse.utilities.common.date.TimeUtilsTest.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code OffsetDateTimeUtilsTest} tests the {@link OffsetDateTimeUtils}
 */
class OffsetDateTimeUtilsTest {

    // OFFSET
    public static final String ISO_8601_OFFSET_PLUS = "+05:30";
    public static final String ISO_8601_OFFSET_MINUS = "-05:00";
    public static final String INVALID_ISO_8601_OFFSET_MISSING_SIGN = "05:00";
    public static final String INVALID_ISO_8601_OFFSET_MISSING_COLON = "-0500";
    public static final String INVALID_ISO_8601_OFFSET_OUT_OF_RANGE = "18:30";

    // OFFSET DATE TIME
    public static final String ISO_8601_OFFSET_DATE_TIME = ISO_8601_DATE_TIME + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_Z = ISO_8601_DATE_TIME + UTC_LITERAL_Z;
    public static final String ISO_8601_OFFSET_DATE_TIME_PLUS = ISO_8601_DATE_TIME + "+05:30";
    public static final String ISO_8601_OFFSET_DATE_TIME_MINUS = ISO_8601_DATE_TIME + "-05:00";
    public static final String ISO_8601_OFFSET_DATE_TIME_MILLI1 = ISO_8601_DATE_TIME_MILLI1 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_MILLI2 = ISO_8601_DATE_TIME_MILLI2 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_MILLI3 = ISO_8601_DATE_TIME_MILLI3 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_MICRO1 = ISO_8601_DATE_TIME_MICRO1 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_MICRO2 = ISO_8601_DATE_TIME_MICRO2 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_MICRO3 = ISO_8601_DATE_TIME_MICRO3 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_NANO1 = ISO_8601_DATE_TIME_NANO1 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_NANO2 = ISO_8601_DATE_TIME_NANO2 + UTC_OFFSET;
    public static final String ISO_8601_OFFSET_DATE_TIME_NANO3 = ISO_8601_DATE_TIME_NANO3 + UTC_OFFSET;

    // INVALID OFFSET DATE TIME
    public static final String INVALID_ISO_8601_OFFSET_DATE_TIME = ISO_8601_DATE_TIME + "+1:00";
    public static final String INVALID_ISO_8601_OFFSET_DATE_TIME_FORMAT = ISO_8601_OFFSET_DATE_TIME_Z + UTC_OFFSET;
    public static final String INVALID_ISO_8601_OFFSET_DATE_TIME_FRACTION_OF_SECOND = ISO_8601_DATE_TIME_NANO3 + ONE + UTC_OFFSET;

    /**
     * Test whether the offset date times are in ISO 8601 date time format {@code YYYY_MM_DDTHH:mm:ss.SSSSSSSSS+hh:mm};
     * where the fraction of seconds is optional and with Z for UTC or +/- the offset from UTC.
     *
     * @param offsetDateTime the offset date time
     * @see OffsetDateTime
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_OFFSET_DATE_TIME, ISO_8601_OFFSET_DATE_TIME_PLUS, ISO_8601_OFFSET_DATE_TIME_MINUS,
            ISO_8601_OFFSET_DATE_TIME_MILLI1, ISO_8601_OFFSET_DATE_TIME_MILLI2, ISO_8601_OFFSET_DATE_TIME_MILLI3,
            ISO_8601_OFFSET_DATE_TIME_MICRO1, ISO_8601_OFFSET_DATE_TIME_MICRO2, ISO_8601_OFFSET_DATE_TIME_MICRO3,
            ISO_8601_OFFSET_DATE_TIME_NANO1, ISO_8601_OFFSET_DATE_TIME_NANO2, ISO_8601_OFFSET_DATE_TIME_NANO3})
    void isIso8601Time_givenValidIso8601OffsetDateTime_expectTrue(String offsetDateTime) {
        assertTrue(OffsetDateTimeUtils.isIso8601OffsetDateTime(offsetDateTime));
    }

    /**
     * Test whether the offset date times not in ISO 8601 date time format {@code YYYY_MM_DDTHH:mm:ss.SSSSSSSSS+hh:mm};
     * where the fraction of seconds is optional and with Z for UTC or +/- the offset from UTC.
     * Null and empty values are also tested.
     *
     * @param offsetDateTime the offset date time
     * @see OffsetDateTime
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_OFFSET_DATE_TIME, INVALID_ISO_8601_OFFSET_DATE_TIME_FORMAT, INVALID_ISO_8601_OFFSET_DATE_TIME_FRACTION_OF_SECOND})
    void isIso8601Time_givenInvalidIso8601OffsetDateTime_expectFalse(String offsetDateTime) {
        assertFalse(OffsetDateTimeUtils.isIso8601OffsetDateTime(offsetDateTime));
    }

    /**
     * Test whether the offset is in ISO 8601 format {@code Z} or {@code +/-hh:mm}.
     *
     * @param offset the offset
     * @see ZoneOffset
     */
    @ParameterizedTest
    @ValueSource(strings = {UTC_LITERAL_Z, UTC_OFFSET, ISO_8601_OFFSET_PLUS, ISO_8601_OFFSET_MINUS})
    void isIso8601Offset_givenValidIso8601Offset_expectTrue(String offset) {
        assertTrue(OffsetDateTimeUtils.isIso8601Offset(offset));
    }

    /**
     * Test whether the offset date times not in ISO format {@code Z} or {@code +/-hh:mm}.
     * Null and empty values are also tested.
     *
     * @param offset the offset
     * @see ZoneOffset
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_OFFSET_MISSING_SIGN, INVALID_ISO_8601_OFFSET_MISSING_COLON, INVALID_ISO_8601_OFFSET_OUT_OF_RANGE})
    void isIso8601Offset_givenInvalidIso8601Offset_expectFalse(String offset) {
        assertFalse(OffsetDateTimeUtils.isIso8601Offset(offset));
    }

    /**
     * Test the conversion of a valid ISO 8601 date-time value to an ISO 8601 offset date time.
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_DATE, ISO_8601_DATE_TIME, ISO_8601_DATE_SPACE_ISO_8601_TIME,
            ISO_8601_DATE_TIME_MILLI1, ISO_8601_DATE_TIME_MILLI2, ISO_8601_DATE_TIME_MILLI3,
            ISO_8601_DATE_TIME_MICRO1, ISO_8601_DATE_TIME_MICRO2, ISO_8601_DATE_TIME_MICRO3,
            ISO_8601_DATE_TIME_NANO1, ISO_8601_DATE_TIME_NANO2, ISO_8601_DATE_TIME_NANO3,
            ISO_8601_OFFSET_DATE_TIME, ISO_8601_OFFSET_DATE_TIME_Z, ISO_8601_OFFSET_DATE_TIME_PLUS, ISO_8601_OFFSET_DATE_TIME_MINUS,
            ISO_8601_OFFSET_DATE_TIME_MILLI1, ISO_8601_OFFSET_DATE_TIME_MILLI2, ISO_8601_OFFSET_DATE_TIME_MILLI3,
            ISO_8601_OFFSET_DATE_TIME_MICRO1, ISO_8601_OFFSET_DATE_TIME_MICRO2, ISO_8601_OFFSET_DATE_TIME_MICRO3,
            ISO_8601_OFFSET_DATE_TIME_NANO1, ISO_8601_OFFSET_DATE_TIME_NANO2, ISO_8601_OFFSET_DATE_TIME_NANO3})
    void convertToIso8601OffsetDateTime_givenValidIso8601DateTimeValue_expectedIso8601OffsetDateTime(String dateTimeValue) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(dateTimeValue);
        assertDoesNotThrow(() -> OffsetDateTime.parse(iso8601OffsetDateTime));
    }

    /**
     * Test the conversion of an invalid ISO 8601 date-time value to an ISO 8601 offset date time.
     * Null and empty values are also tested.
     *
     * @param dateTimeValue the date-time value
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, INVALID_ISO_8601_OFFSET_DATE_TIME})
    void convertToIso8601OffsetDateTime_givenInvalidIso8601DateTimeValue_expectedSameValue(String dateTimeValue) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(dateTimeValue);
        assertEquals(dateTimeValue, iso8601OffsetDateTime);
    }

    /**
     * Test the conversion of an ISO 8601 date-time value and an ISO 8601 offset to an ISO 8601 offset date time.
     */
    @ParameterizedTest
    @ValueSource(strings = {ISO_8601_DATE, ISO_8601_DATE_TIME, ISO_8601_DATE_SPACE_ISO_8601_TIME,
            ISO_8601_DATE_TIME_MILLI1, ISO_8601_DATE_TIME_MILLI2, ISO_8601_DATE_TIME_MILLI3,
            ISO_8601_DATE_TIME_MICRO1, ISO_8601_DATE_TIME_MICRO2, ISO_8601_DATE_TIME_MICRO3,
            ISO_8601_DATE_TIME_NANO1, ISO_8601_DATE_TIME_NANO2, ISO_8601_DATE_TIME_NANO3,
            ISO_8601_OFFSET_DATE_TIME, ISO_8601_OFFSET_DATE_TIME_Z, ISO_8601_OFFSET_DATE_TIME_PLUS, ISO_8601_OFFSET_DATE_TIME_MINUS,
            ISO_8601_OFFSET_DATE_TIME_MILLI1, ISO_8601_OFFSET_DATE_TIME_MILLI2, ISO_8601_OFFSET_DATE_TIME_MILLI3,
            ISO_8601_OFFSET_DATE_TIME_MICRO1, ISO_8601_OFFSET_DATE_TIME_MICRO2, ISO_8601_OFFSET_DATE_TIME_MICRO3,
            ISO_8601_OFFSET_DATE_TIME_NANO1, ISO_8601_OFFSET_DATE_TIME_NANO2, ISO_8601_OFFSET_DATE_TIME_NANO3})
    void convertToIso8601OffsetDateTime_givenValidIso8601DateTimeValueAndIso8601Offset_expectedIso8601OffsetDateTime(String dateTimeValue) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(dateTimeValue, UTC_OFFSET);
        assertDoesNotThrow(() -> OffsetDateTime.parse(iso8601OffsetDateTime));
    }

    /**
     * Test the conversion of an invalid ISO 8601 date-time value and an ISO 8601 offset to an ISO 8601 offset date time.
     * Null and empty values are also tested.
     *
     * @param dateTimeValue the date-time value
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, INVALID_ISO_8601_OFFSET_DATE_TIME})
    void convertToIso8601OffsetDateTime_givenInvalidIso8601DateTimeValueAndIso8601Offset_expectedSameValue(String dateTimeValue) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(dateTimeValue, UTC_OFFSET);
        assertEquals(dateTimeValue, iso8601OffsetDateTime);
    }

    /**
     * Test the conversion of a valid ISO 8601 date-time value and valid ISO 8601 offset to an ISO 8601 offset date time.
     *
     * @param offset the offset
     */
    @ParameterizedTest
    @ValueSource(strings = {UTC_LITERAL_Z, UTC_OFFSET, ISO_8601_OFFSET_PLUS, ISO_8601_OFFSET_MINUS})
    void convertToIso8601OffsetDateTime_givenIso8601DateTimeAndValidIso8601Offset_expectedIso8601OffsetDateTime(String offset) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(ISO_8601_DATE_TIME, offset);
        assertDoesNotThrow(() -> OffsetDateTime.parse(iso8601OffsetDateTime));
    }

    /**
     * Test the conversion of a valid ISO 8601 date-time value and invalid ISO 8601 offset to an ISO 8601 offset date time.
     * Null and empty values are also tested.
     *
     * @param offset the offset
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_OFFSET_MISSING_SIGN, INVALID_ISO_8601_OFFSET_MISSING_COLON, INVALID_ISO_8601_OFFSET_OUT_OF_RANGE})
    void convertToIso8601OffsetDateTime_givenIso8601DateTimeAndInvalidIso8601Offset_expectedSameValue(String offset) {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(ISO_8601_DATE_TIME, offset);
        assertEquals(ISO_8601_DATE_TIME, iso8601OffsetDateTime);
    }

    /**
     * Test the conversion of an invalid ISO 8601 date-time value and an invalid ISO 8601 offset to an ISO 8601 offset date time.
     */
    @Test
    void convertToIso8601OffsetDateTime_givenInvalidIso8601DateTimeValueAndInvalidIso8601Offset_expectedSameValue() {
        String iso8601OffsetDateTime = OffsetDateTimeUtils.convertToIso8601OffsetDateTime(INVALID_ISO_8601_DATE, INVALID_ISO_8601_OFFSET_MISSING_SIGN);
        assertEquals(INVALID_ISO_8601_DATE, iso8601OffsetDateTime);
    }

}
