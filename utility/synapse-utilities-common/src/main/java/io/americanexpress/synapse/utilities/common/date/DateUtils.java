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

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.StringJoiner;
import java.util.regex.Pattern;


/**
 * {@code DateUtils} provides methods for date conversions and validations.
 */
public class DateUtils {

    /**
     * Logger for the controller.
     */
    protected static final XLogger logger = XLoggerFactory.getXLogger(DateUtils.class);

    /**
     * The compiles regular expression for an ISO 8601 date format.
     */
    public static final Pattern ISO8601_DATE_PATTERN = Pattern.compile("^(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)$");

    private DateUtils() {
        //Private constructor to prevent instantiation of utils class.
    }

    /**
     * Convert from local date to xml gregorian calendar xml gregorian calendar.
     *
     * @param date the date
     * @return the xml gregorian calendar
     */
    public static XMLGregorianCalendar convertFromLocalDateToXmlGregorianCalendar(LocalDate date) {
        XMLGregorianCalendar xcal = null;
        if (date != null) {
            try {
                xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
            } catch (DatatypeConfigurationException exception) {
                logger.warn("Error while converting from local date to xml gregorian calendar", exception);
                //This one will be handled by the handlesApplicationException method first if, when cause != null, since cause is the exception that we pass here.
                throw new ApplicationServerException(exception);
            }
        }
        return xcal;
    }

    /**
     * Convert from xml gregorian calendar to local date local date.
     *
     * @param xcal the xcal
     * @return the local date
     */
    public static LocalDate convertFromXmlGregorianCalendarToLocalDate(XMLGregorianCalendar xcal) {
        LocalDate localDate = null;
        if (xcal != null) {
            localDate = xcal.toGregorianCalendar().toZonedDateTime().toLocalDate();
            logger.debug("Local date {}", localDate);
        }
        return localDate;
    }

    /**
     * Comparison between dates.
     *
     * @param datePattern the date pattern
     * @param dateText1   the date text 1
     * @param dateText2   the date text 2
     * @return the int
     */
    public static int compareTo(String datePattern, String dateText1, String dateText2) {
        int compareToValue = Integer.MAX_VALUE;
        LocalDate localDate1 = toLocalDate(datePattern, dateText1);
        if (localDate1 != null) {
            LocalDate localDate2 = toLocalDate(datePattern, dateText2);
            if (localDate2 != null) {
                compareToValue = localDate1.compareTo(localDate2);
            }
        }
        return compareToValue;
    }

    /**
     * Convert to local date.
     *
     * @param datePattern the date pattern
     * @param dateText    the date text
     * @return the local date
     */
    public static LocalDate toLocalDate(String datePattern, String dateText) {
        LocalDate localDate = null;
        if (StringUtils.isNotBlank(datePattern) && StringUtils.isNotBlank(dateText)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
            try {
                localDate = LocalDate.parse(dateText, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                logger.warn("Date time could not be parsed", e);
            }
        }
        return localDate;
    }

    /**
     * This method converts LocalDate to Date
     *
     * @param dateToConvert the date to convert
     * @return date of type Date
     */
    public static Date convertFromLocalDateToDate(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * /**
     * Check if the value matches the ISO 8601 standard for date and time format.
     * For example, 2021-09-23 is valid.
     * Values should be formatted as {@code YYYY-MM-DD}.
     * <p>
     * Valid values can be parsed by calling {@link LocalDateTime#parse(CharSequence)}.
     * <p>
     * For more details on ISO 8601 standard see https://www.w3.org/TR/NOTE-datetime
     *
     * @param date the date
     * @return true if valid
     */
    public static boolean isIso8601Date(String date) {
        return StringUtils.isNotBlank(date) && ISO8601_DATE_PATTERN.matcher(date).matches();
    }

    /**
     * Check if the date is a valid date in YYYYMMDD format.
     *
     * @param date the date
     * @return true if valid date
     */
    public static boolean isYyyyMmDdDate(String date) {
        String formattedDate = date;
        boolean isValid = false;

        // Convert the date to ISO 8601 format.
        if (StringUtils.isNumeric(date) && formattedDate.length() == 8) {
            formattedDate = new StringJoiner("-").add(date.substring(0, 4)).add(date.substring(4, 6)).add(date.substring(6, 8)).toString();
            isValid = isIso8601Date(formattedDate);
        }

        return isValid;
    }

    /**
     * Convert a date time string to LocalDateTime object given a specific date time format.
     *
     * @param dateTimePattern the expected pattern of the date time
     * @param dateTimeText    the string containing the date time
     * @return a LocalDateTime object of the dateTimeText
     */
    static LocalDateTime toLocalDateTime(String dateTimePattern, String dateTimeText) {
        LocalDateTime localDateTime;
        localDateTime = parseLocalDateTime(dateTimeText, dateTimePattern);
        return localDateTime;
    }

    /**
     * Local Date Time parser takes a string of the local date and the date pattern and convert from String to Local Date Time.
     *
     * @param stringLocalDateTime  string of the Local Date Time
     * @param localDateTimePattern pattern of the Local Date Time
     * @return object of type LocalDateTime
     */
    private static LocalDateTime parseLocalDateTime(String stringLocalDateTime, String localDateTimePattern) {
        LocalDateTime localDateTime = null;

        if (StringUtils.isNotBlank(localDateTimePattern) && StringUtils.isNotBlank(stringLocalDateTime)) {
            try {
                localDateTime = LocalDateTime.parse(stringLocalDateTime, DateTimeFormatter.ofPattern(localDateTimePattern));
            } catch (DateTimeParseException dateTimeParseException) {
                logger.debug("Date time could not be parsed from backend API for String date: {} and pattern: {}", stringLocalDateTime, localDateTimePattern);
            }
        }
        return localDateTime;
    }

    /**
     * This method converts from LocalDateType to Long Epoch Milliseconds.
     *
     * @param localDateTime input local date time parameter
     * @return milliseconds epoch of datatype long
     */
    public static long convertFromLocalDateTimeToLongEpochMilliSeconds(LocalDateTime localDateTime) {
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
