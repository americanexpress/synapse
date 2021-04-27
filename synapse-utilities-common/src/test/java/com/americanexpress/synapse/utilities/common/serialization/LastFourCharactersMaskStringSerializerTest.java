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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastFourCharactersMaskStringSerializerTest extends BaseTestStringSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("masking_numbers");
    }

    @Override
    public void arrangeNullField() {
        model.setMaskingNumbers(null);
    }

    @Override
    public void arrangeEmptyValue() {
        model.setMaskingNumbers("");
    }

    @Override
    public void arrangeWhiteSpace() {
        model.setMaskingNumbers(" ");
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        model.setMaskingNumbers("1234567890");
        String expected = "{\"" + testField + "\":\"******7890\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, "Serialization expected well formatted numbers.");
    }

    @Test
    public void serialize_anyString() throws Exception {
        model.setMaskingNumbers(" 76767abc77**** ");
        String expected = "{\"masking_numbers\":\"*************** \"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    public void serialize_15digitNumbers() throws Exception {
        model.setMaskingNumbers(" 123451234512345 ");
        String expected = "{\"masking_numbers\":\"*************345 \"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    public void serialize_length10StringUntrimmed() throws Exception {
        model.setMaskingNumbers(" abcdefghij ");
        String expected = "{\"masking_numbers\":\"********hij \"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    public void serialize_ssn() throws Exception {
        model.setMaskingNumbers(" 1233456789");
        String expected = "{\"masking_numbers\":\"*******6789\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }


    @Test
    public void serialize_whiteSpace() throws Exception {
        arrangeWhiteSpace();
        String expected = "{\"masking_numbers\":\" \"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }
}
