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
package com.americanexpress.synapse.utilities.common.date;

import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtils {

    /**
     * Logger for the controller.
     */
    protected static final XLogger logger = XLoggerFactory.getXLogger(DateUtils.class);

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

    public static LocalDate convertFromXmlGregorianCalendarToLocalDate(XMLGregorianCalendar xcal) {
        LocalDate localDate = null;
        if (xcal != null) {
            localDate = xcal.toGregorianCalendar().toZonedDateTime().toLocalDate();
            logger.debug("Local date {}", localDate);
        }
        return localDate;
    }

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
     * @param dateToConvert
     * @return date of type Date
     */
    public static Date convertFromLocalDateToDate(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
}
