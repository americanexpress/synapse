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

import java.io.IOException;
import java.util.List;

/**
 * TitleCaseStringListSerializer class serializes a list of strings into its title case format.
 * e.g. {JOHN X DOE, JANE A SMITH} will be converted into {John X Doe, Jane A Smith}.
 *
 * @author Paolo Claudio
 */
public class TitleCaseStringListSerializer extends StringListSerializer {

    /**
     * Convert each string in this list into title case format.
     *
     * @param list      to be converted
     * @param generator JSON generator
     * @param provider  serializer provider
     * @throws IOException whenever there's an issue performing the serialization
     */
    @Override
    public void serialize(List<String> list, JsonGenerator generator, SerializerProvider provider) throws IOException {
        TitleCaseStringSerializer titleCaseStringSerializer = new TitleCaseStringSerializer();
        String text;
        for (int i = 0; i < list.size(); i++) {
            text = list.get(i);
            text = titleCaseStringSerializer.serialize(text);
            list.set(i, text);
        }
        generator.writeObject(list);
    }
}
