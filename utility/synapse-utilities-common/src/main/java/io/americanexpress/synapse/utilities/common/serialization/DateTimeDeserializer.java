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

import io.americanexpress.synapse.utilities.common.date.DateFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code DateTimeDeserializer} deserializes string to date in datetime format.
 * First the string is deserialized using "yyyy-MM-dd HH:mm:ss" format. If that fails, then ISO 8601 format is used
 * via {@link DateTimeFormatter#ISO_DATE_TIME}.
 */
public class DateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String jsonValue = jsonParser.getValueAsString();

        try {
            return LocalDateTime.parse(jsonValue, DateTimeFormatter.ofPattern(DateFormat.ISO_TIME.getValue()));
        } catch (Exception e) {
            return LocalDateTime.parse(jsonValue, DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
