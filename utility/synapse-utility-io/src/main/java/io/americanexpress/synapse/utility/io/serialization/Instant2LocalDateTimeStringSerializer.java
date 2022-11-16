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
package io.americanexpress.synapse.utility.io.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.americanexpress.synapse.utilities.common.date.DateFormat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Instant2LocalDateTimeStringSerializer class is used to convert an ISO_INSTANT String format '2011-12-03T10:15:30Z' to an ISO_LOCAL_DATE_TIME String format '2011-12-03T10:15:30'.
 *
 * @author Paolo Claudio
 */
public class Instant2LocalDateTimeStringSerializer extends StringSerializer {

    /**
     * Convert from the ISO_INSTANT String format '2011-12-03T10:15:30Z' to an ISO_LOCAL_DATE_TIME String format '2011-12-03T10:15:30'.
     *
     * @param text      to be converted into uppercase
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }

    /**
     * Performs the formatting for the serializer.
     *
     * @param text text to be formatted.
     * @return the formatted text.
     */
    @Override
    public String serialize(String text) {
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern(DateFormat.ISO_INSTANT.getValue());
        LocalDateTime originalDateTime = LocalDateTime.parse(text, originalFormatter);
        DateTimeFormatter convertedFormatter = DateTimeFormatter.ofPattern(DateFormat.ISO_TIME.getValue());
        return originalDateTime.format(convertedFormatter);
    }
}
