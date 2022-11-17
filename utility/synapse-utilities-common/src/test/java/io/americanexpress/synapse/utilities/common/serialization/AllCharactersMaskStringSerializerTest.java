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

import io.americanexpress.synapse.utilities.common.serialization.model.SampleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code AllCharactersMaskStringSerializerTest} tests the {@link AllCharactersMaskStringSerializer}
 */
class AllCharactersMaskStringSerializerTest extends BaseTestStringSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("allNumbersMasking");
    }

    @Override
    public void arrangeWhiteSpace() {model.setAllNumbersMasking(" "); }

    @Override
    public void arrangeNullField() {model.setAllNumbersMasking(null);}

    @Override
    public void arrangeEmptyValue() {model.setAllNumbersMasking(""); }

    @Test
    public void serialize_whiteSpace() throws Exception {
        arrangeWhiteSpace();
        String expected = "{\"allNumbersMasking\":\" \"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    void serialize_null() throws Exception {
        SampleModel model = null;
        String expected = "null";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    void serialize_4digitMasking() throws Exception {
        model.setAllNumbersMasking("1234");
        String expected = "{\"allNumbersMasking\":\"****\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        model.setAllNumbersMasking("5243524abcdef");
        String expected = "{\"" + testField + "\":\"*************\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, "Serialization expected well formatted numbers.");
    }
}
