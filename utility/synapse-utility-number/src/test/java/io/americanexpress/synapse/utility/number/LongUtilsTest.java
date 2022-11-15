package io.americanexpress.synapse.utility.number;

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongUtilsTest {

    @Test
    void tryParseLong_givenNull_expectedNull() {
        Long actual = LongUtils.tryParseLong(null);
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given1234_expected1234() {
        Long actual = LongUtils.tryParseLong("1234");
        Long expected = 1234L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenABC_expectedNull() {
        Long actual = LongUtils.tryParseLong("ABC");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenABC123_expectedNull() {
        Long actual = LongUtils.tryParseLong("ABC123");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given12345646WithDecimals_expectedNull() {
        Long actual = LongUtils.tryParseLong("12345646675.00000");
        Long expected = null;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenNegative123_expectedNegative123() {
        Long actual = LongUtils.tryParseLong("-123");
        Long expected = -123L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given000_expected000() {
        Long actual = LongUtils.tryParseLong("000");
        Long expected = 000L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_given0_expected0() {
        Long actual = LongUtils.tryParseLong("", 0L);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenInvalidString_expectedDefaultValue0() {
        Long actual = LongUtils.tryParseLong("invalid", 0L);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void tryParseLong_givenNumericString23_expected23() {
        Long actual = LongUtils.tryParseLong("23", 0L);
        Long expected = 23L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void getValue_givenNull_expected0() {
        Long actual = LongUtils.getValue(null);
        Long expected = 0L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void getValue_givenValidLong23_expected23() {
        Long actual = LongUtils.getValue(23L);
        Long expected = 23L;
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}
