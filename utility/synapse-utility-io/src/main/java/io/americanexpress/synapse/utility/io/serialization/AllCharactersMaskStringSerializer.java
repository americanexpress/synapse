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
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * {@code AllCharactersMaskStringSerializer} class is used to convert the original value to the masked value during serialization.
 * e.g. 1234 will be converted into **** by default.
 * The other static serialize method included in this serializer is used to mask a specific amount of characters.
 *
 */
public class AllCharactersMaskStringSerializer extends StringSerializer {
    private static final String MASKED_CHARACTER = "*";
    private static final String MASKING_REGEX = ".(?=.{%s})";

    /**
     * Mask all of the characters in the string.
     *
     * @param text to be serialized
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeObject(serialize(text));
    }

    @Override
    public String serialize(String text) {
        return serialize(text, 0);
    }

    /**
     * Used to leave a specific amount of end characters unmasked while the rest is masked.
     *
     * @param text                    the text to be serialized
     * @param unmaskedCharactersCount the number of characters at the end of the string that will not be masked.
     * @return the masked string.
     */
    public static String serialize(String text, int unmaskedCharactersCount) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        // Mask all of the characters in the array
        return text.replaceAll(String.format(MASKING_REGEX, String.valueOf(unmaskedCharactersCount)), MASKED_CHARACTER);
    }
}

