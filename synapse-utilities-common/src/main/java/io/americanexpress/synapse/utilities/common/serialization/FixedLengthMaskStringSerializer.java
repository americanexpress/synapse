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
 * FixedLengthMaskStringSerializer class used  to convert a original value into masking value during serialization.
 * e.g. if the text is 123456879 then the text will be serialized as ***
 *
 */
public class FixedLengthMaskStringSerializer extends StringSerializer {

    private static final String FIXED_LENGTH_MASK = "***";

    /**
     * Convert the text field into fixed length masking value.
     *
     * @param text               to be converted into lowercase
     * @param jsonGenerator      JSON generator
     * @param serializerProvider serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(serialize(text));
    }

    /**
     * Mask all of the characters in the string.
     *
     * @param text to be serialized to FIXED_LENGTH_MASK
     */
    @Override
    public String serialize(String text) {
        return FIXED_LENGTH_MASK;
    }
}
