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
 * {@code UnitedStatesAbbreviationStringSerializer} class is used to convert a fully qualified U.S. state name into its abbreviated form during serialization.
 * For example, "Florida" will get formatted to "FL".
 *
 * @author Paolo Claudio
 */
public class UnitedStatesAbbreviationStringSerializer extends StringSerializer {

    /**
     * Convert the fully qualified U.S. state name into its abbreviated form.
     *
     * @param text      fully qualified U.S. state name
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(String text, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeObject(serialize(text));
    }

    /**
     * Convert the fully qualified U.S. state name into its abbreviated form.
     *
     * @param text fully qualified U.S. state name.
     * @return the fully qualifified U.S. state name in string form.
     */
    @Override
    public String serialize(String text) {
        return UnitedState.getAbbreviation(text).trim();
    }
}
