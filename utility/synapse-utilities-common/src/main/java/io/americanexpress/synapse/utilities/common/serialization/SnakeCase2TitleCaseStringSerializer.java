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
 * TitleCaseStringSerializer class serializes a string into its title case format.
 * e.g. JOHN X DOE will be converted into John X Doe.
 * e.g. JOHN_X_DOE will be converted into John X Doe.
 * e.g. ANNE-MARIE O'REILLEY will be converted into Anne-Marie O'Reilly.
 * e.g. A.K. COWLING will be converted into A.K. Cowling.
 *
 */
public class SnakeCase2TitleCaseStringSerializer extends StringSerializer {

    /**
     * Serialize this text into title case.
     * e.g. JOHN X DOE will be converted into John X Doe.
     * e.g. JOHN_X_DOE will be converted into John X Doe.
     * e.g. ANNE-MARIE O'REILLEY will be converted into Anne-Marie O'Reilly.
     * e.g. A.K. COWLING will be converted into A.K. Cowling.
     *
     * @param text name to be converted
     * @return the title cased conversion of this name
     */
    public String serialize(String text) {

        TitleCaseStringSerializer titleCaseStringSerializer = new TitleCaseStringSerializer();

        if (text != null) {
            text = text.replaceAll("_", " ");
            text = titleCaseStringSerializer.serialize(text);
        }
        return text;
    }

    /**
     * Convert the text into title case format.
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

