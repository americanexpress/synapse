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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code IntegerSerializerTest} tests the {@link IntegerSerializer}.
 */
class IntegerSerializerTest extends BaseTestSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("numbers");
    }

    @Override
    public void arrangeNullField() {model.setBalanceMasking(null);}

    @Override
    public void arrangeEmptyValue() {}

    @Override
    public String getExpectedEmptyValue() {
        return "{}";
    }

    @Test
    void serialize_clean10digitNumber() throws Exception {
        model.setNumbers(12345678);
        String expected = "{\"numbers\":\"12,345,678\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_cleanNegativeNumber() throws Exception {
        model.setNumbers(-1234);
        String expected = "{\"numbers\":\"-1,234\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_cleanZero() throws Exception {
        model.setNumbers(0);
        String expected = "{\"numbers\":\"0\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_cleanMultipleZeros() throws Exception {
        model.setNumbers(00000);
        String expected = "{\"numbers\":\"0\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_emptyModel() throws Exception {
        String expected = "{}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_EXPECTED_EMPTY);
    }

    @Override
    @Test
    public void serialize_clean() throws Exception {
        model.setNumbers(1234);
        String expected = "{\"numbers\":\"1,234\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }
}
