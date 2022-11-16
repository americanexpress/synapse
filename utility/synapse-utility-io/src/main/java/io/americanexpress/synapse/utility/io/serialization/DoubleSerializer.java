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
 * DoubleSerializer class serializes Double objects into a decimal format.
 *
 * @author Paolo Claudio
 */
abstract class DoubleSerializer extends NumberSerializer<Double> {

    /**
     * Serialize the double.
     *
     * @param value              Value to serialize; can not be null.
     * @param jsonGenerator      Generator used to output resulting Json content
     * @param serializerProvider Provider that can be used to get serializers for serializing Objects value contains, if any.
     */
    @Override
    public void serialize(Double value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(serialize(value));
    }

    /**
     * Serialize the string representation of the decimal.
     *
     * @param text to be converted into a Double
     * @return the serialized string representation of the decimal
     */
    public String serialize(String text) {
        String serializedValue = text;
        if (StringUtils.isNotBlank(text)) {
            text = text.trim();
            Double value;
            try {
                value = Double.valueOf(text);
                serializedValue = serialize(value);
            } catch (NumberFormatException numberFormatException) {
                logger.warn("An exception occurred while trying to create the Double from " + text, numberFormatException);
            }
        }
        return serializedValue;
    }
}
