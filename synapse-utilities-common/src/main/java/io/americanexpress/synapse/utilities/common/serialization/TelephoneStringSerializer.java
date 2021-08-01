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
package io.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * TelephoneStringSerializer class is used to convert an unformatted phone number String field into the (xxx) xxx-xxxx format during serialization.
 *
 * @author Paolo Claudio
 */
public class TelephoneStringSerializer extends StringSerializer {

    /**
     * Length of a well-formatted phone number.
     */
    private static final int PHONE_NUMBER_LENGTH = 10;

    /**
     * Index of the starting position of the area code in a phone number which is the first 3 numbers of a phone number.
     * e.g. for the phone number (123) 456-7890, the area code is 123.
     */
    private static final int PHONE_AREA_CODE_INDEX = 0;

    /**
     * Index of the starting position of the phone prefix in a phone number which is the second 3 numbers of a phone number.
     * e.g. for the phone number (123) 456-7890, the phone prefix is 456.
     */
    private static final int PHONE_PREFIX_INDEX = 3;

    /**
     * Index of the starting position of the phone line number in a phone number which is the last 4 numbers of a phone number.
     * e.g. for the phone number (123) 456-7890, the phone line number is 7890.
     */
    private static final int PHONE_LINE_NUMBER_INDEX = 6;

    /**
     * Convert the phone number field into the (xxx) xxx-xxxx format.
     *
     * @param text      to be converted into into the (xxx) xxx-xxxx format
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }

    /**
     * Formats the text as a phone number if identified as a phone number.
     *
     * @param text the potential phone number to be formatted.
     * @return the formatted phone number.
     */
    public String serialize(String text) {
        String phoneNumber = text.trim();
        if (StringUtils.isNotBlank(phoneNumber)) {
            if (phoneNumber.length() == PHONE_NUMBER_LENGTH && phoneNumber.matches("\\d{" + PHONE_NUMBER_LENGTH + "}")) {
                String areaCode = phoneNumber.substring(PHONE_AREA_CODE_INDEX, PHONE_PREFIX_INDEX);
                String phonePrefix = phoneNumber.substring(PHONE_PREFIX_INDEX, PHONE_LINE_NUMBER_INDEX);
                String lineNumber = phoneNumber.substring(PHONE_LINE_NUMBER_INDEX);
                phoneNumber = "(" + areaCode + ") " + phonePrefix + "-" + lineNumber;
            }
        }
        return phoneNumber;
    }
}
