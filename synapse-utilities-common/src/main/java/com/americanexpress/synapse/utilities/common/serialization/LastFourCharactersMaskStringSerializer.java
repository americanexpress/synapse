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
package com.americanexpress.synapse.utilities.common.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * LastFourCharactersMaskStringSerializer class masks the last 4 characters in a string.
 * e.g. if the text is 123456879 then the text will be serialized as *****6789
 *
 * @author Paolo Claudio
 */
public class LastFourCharactersMaskStringSerializer extends AllCharactersMaskStringSerializer {

    /**
     * Mask all of the characters in the string except for the last 4 characters.
     *
     * @param text to be serialized
     * @return the masked string except for the last 4 characters
     */
    public String serialize(String text) {
        return super.serialize(text, 4);
    }

    /**
     * Mask the last 4 characters of a string.
     *
     * @param text      to be converted
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }
}
