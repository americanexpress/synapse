package io.americanexpress.synapse.utilities.common.date;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code DateUtilsTest} tests the {@link DateUtils}
 */
@ExtendWith(MockitoExtension.class)
class DateUtilsTest {

    // DATES
    public static final String ISO_8601_DATE = "2021-09-24";
    public static final String YYYYMMDD_DATE = "20210924";

    // INVALID DATES
    public static final String INVALID_ISO_8601_DATE = "2021-24-09";
    public static final String INVALID_YYYYMMDD_DATE = "20212409";

    /**
     * Test whether the date are in ISO 8601 date format {@code YYYY-MM-DD}.
     *
     * @see LocalDate
     */
    @Test
    void isIso8601Date_givenValidIso8601Date_expectTrue() {
        assertTrue(DateUtils.isIso8601Date(ISO_8601_DATE));
    }

    /**
     * Test whether the dates are not in ISO 8601 date format {@code YYYY-MM-DD}.
     * Null and empty values are also tested.
     *
     * @param date the date
     * @see LocalDate
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_ISO_8601_DATE, YYYYMMDD_DATE})
    void isIso8601Date_givenInvalidIso8601Date_expectFalse(String date) {
        assertFalse(DateUtils.isIso8601Date(date));
    }

    /**
     * Test whether the dates are in YYYYMMDD format.
     */
    @Test
    void isValidYyyyMmDdDate_givenValidYyyyMmDdDate_expectTrue() {
        assertTrue(DateUtils.isYyyyMmDdDate(YYYYMMDD_DATE));
    }

    /**
     * Test whether the dates are not in YYYYMMDD format.
     * Null and empty values are also tested.
     *
     * @param date the date
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {INVALID_YYYYMMDD_DATE, ISO_8601_DATE})
    void isValidYyyyMmDdDate_givenInvalidYyyyMmDdDate_expectFalse(String date) {
        assertFalse(DateUtils.isYyyyMmDdDate(date));
    }

    /**
     * To local date time given valid date time expect valid local date time its returned.
     */
    @Test
    void toLocalDateTime_givenValidDateTime_expectValidLocalDateTimeItsReturned() {
        LocalDateTime actual = DateUtils.toLocalDateTime(DateTimeFormat.ISO_LOCAL_DATE_TIME_SSS.getValue(), MockDate.ISO_LOCAL_DATE_TIME_SSS);
        assertEquals(LocalDateTime.parse(MockDate.ISO_LOCAL_DATE_TIME_SSS), actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    /**
     * To local date time given null string date time expect return null.
     */
    @Test
    void toLocalDateTime_givenNullStringDateTime_expectReturnNull() {
        assertNull(DateUtils.toLocalDateTime(DateTimeFormat.ISO_LOCAL_DATE_TIME_SSS.getValue(), null), CommonAssertionMessages.VALUE_NOT_NULL);
    }

    /**
     * To local date time given incorrect date time format expect does not throw exception.
     */
    @Test
    void toLocalDateTime_givenIncorrectDateTimeFormat_expectDoesNotThrowException() {
        assertNull(DateUtils.toLocalDateTime(DateFormat.ISO_DATE.getValue(), MockDate.ISO_LOCAL_DATE_TIME_SSS), CommonAssertionMessages.VALUE_NOT_NULL);
        Assertions.assertDoesNotThrow(() -> DateUtils.toLocalDateTime(DateFormat.ISO_DATE.getValue(), MockDate.ISO_LOCAL_DATE_TIME_SSS), CommonAssertionMessages.EXCEPTION_IS_THROWN);
    }

    /**
     * To local date time given incorrect string date format expect does not throw exception.
     */
    @Test
    void toLocalDateTime_givenIncorrectStringDateFormat_expectDoesNotThrowException() {
        assertNull(DateUtils.toLocalDateTime(DateTimeFormat.ISO_LOCAL_DATE_TIME_SSS.getValue(), MockDate.ISO_DATE), CommonAssertionMessages.VALUE_NOT_NULL);
        Assertions.assertDoesNotThrow(() -> DateUtils.toLocalDateTime(DateTimeFormat.ISO_LOCAL_DATE_TIME_SSS.getValue(), MockDate.ISO_DATE), CommonAssertionMessages.EXCEPTION_IS_THROWN);
    }

}