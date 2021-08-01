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

import java.io.IOException;

/**
 * NationalIdentifierStringSerializer class is used to convert an unformatted ssn/tax identifiers String field into the 000-00-0000 format during serialization.
 *
 */
public class NationalIdentifierStringSerializer extends StringSerializer {

    /**
     * Length of a well-formatted ssn/tax identifier.
     */
    private static final int IDENTIFIERS_LENGTH = 9;

    /**
     * Index of the starting position of the area number in a ssn/tax identifiers which is the first 3 numbers of a ssn/tax identifiers.
     * e.g. for the ssn number 123-45-6789, the area number is 123.
     */
    private static final int AREA_NUMBER_INDEX = 0;

    /**
     * Index of the starting position of the group number in a ssn/tax identifiers which is the second 2 numbers of a ssn/tax identifiers.
     * e.g. for the ssn number 123-45-6789, the group number is 45.
     */
    private static final int GROUP_NUMBER_INDEX = 3;

    /**
     * Index of the starting position of the serial number in a ssn/tax identifiers which is the last 4 numbers of a ssn/tax identifiers.
     * e.g. for the ssn number 123-45-6789, the serial number is 6789.
     */
    private static final int SERIAL_NUMBER_INDEX = 5;

    /**
     * Performs the formatting on ssn/tax identifiers.
     *
     * @param text text to be formatted.
     * @return the formatted SSN or Tax identifier.
     */
    @Override
    public String serialize(String text) {
        String identifiers = text.trim();
        if (identifiers.length() == IDENTIFIERS_LENGTH && identifiers.matches("\\d{" + IDENTIFIERS_LENGTH + "}")) {
            String areaNumber = identifiers.substring(AREA_NUMBER_INDEX, GROUP_NUMBER_INDEX);
            String groupNumber = identifiers.substring(GROUP_NUMBER_INDEX, SERIAL_NUMBER_INDEX);
            String serialNumber = identifiers.substring(SERIAL_NUMBER_INDEX);
            identifiers = areaNumber + "-" + groupNumber + "-" + serialNumber;
        }
        return identifiers;
    }

    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeObject(serialize(text));
    }
}
