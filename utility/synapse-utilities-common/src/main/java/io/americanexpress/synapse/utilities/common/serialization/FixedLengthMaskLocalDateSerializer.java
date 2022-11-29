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
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

/**
 * {@code FixedLengthMaskLocalDateSerializer} serializes local date to fixed length.
 */
public class FixedLengthMaskLocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate date, JsonGenerator generator, SerializerProvider provider) throws IOException {

        AllCharactersMaskStringSerializer allCharactersMaskStringSerializer = new AllCharactersMaskStringSerializer();
        generator.writeString(allCharactersMaskStringSerializer.serialize(date.toString()));
    }
}
