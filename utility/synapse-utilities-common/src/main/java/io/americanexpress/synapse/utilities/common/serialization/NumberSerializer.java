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

import java.io.IOException;
import java.text.NumberFormat;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * {@code NumberSerializer} class specifies the prototypes for serializing Number objects and their descendants.
 *
 * @param <T> type of Number e.g. Integer, Double, BigDecimal
 * @author Paolo Claudio
 */
abstract class NumberSerializer<T extends Number> extends JsonSerializer<T> {

    /**
     * Logger used to track any issues when formatting Number objects.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Used to format the number based on locale.
     */
    protected NumberFormat numberFormat;

    /**
     * Default constructor creates a new instance of NumberSerialize with default values.
     */
    NumberSerializer() {
        setNumberFormat();
    }

    /**
     * Serialize the number.
     *
     * @param value              Value to serialize; can not be null.
     * @param jsonGenerator      Generator used to output resulting Json content
     * @param serializerProvider Provider that can be used to get serializers for serializing Objects value contains, if any.
     */
    @Override
    public void serialize(T value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(serialize(value));
    }

    /**
     * Serialize the number.
     *
     * @param value to be formatted
     * @return the formatted number
     */
    protected String serialize(T value) {
        return numberFormat.format(value);
    }

    /**
     * Prototype to set the number format used for serialization.
     */
    protected abstract void setNumberFormat();
}
