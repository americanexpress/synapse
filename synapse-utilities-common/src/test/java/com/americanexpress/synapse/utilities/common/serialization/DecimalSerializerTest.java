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

import com.americanexpress.synapse.framework.test.CommonAssertionMessages;
import com.americanexpress.synapse.framework.test.MockFixedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DecimalSerializerTest extends BaseTestSerializer {
    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("decimal_value");
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
    public void serialize_10digitDecimal() throws Exception {
        model.setTotalRevenue(Double.valueOf(12345678.009977));
        String expected = "{\"decimal_value\":\"12,345,678.01\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_4digitDecimal() throws Exception {
        model.setTotalRevenue(Double.valueOf(1234.00));
        String expected = "{\"decimal_value\":\"1,234.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_negativeDecimal() throws Exception {
        model.setTotalRevenue(Double.valueOf(-1234.00));
        String expected = "{\"decimal_value\":\"-1,234.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_zeroDecimal() throws Exception {
        model.setTotalRevenue(Double.valueOf(0));
        String expected = "{\"decimal_value\":\"0.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_multipleZerosDecimal() throws Exception {
        model.setTotalRevenue(Double.valueOf(00000));
        String expected = "{\"decimal_value\":\"0.00\"}";
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
        model.setTotalRevenue(Double.valueOf(1234.000000000000));
        String expected = "{\"decimal_value\":\"1,234.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
	void serialize_nullString_text() throws Exception {
		DecimalSerializer serializer = new DecimalSerializer();
		String text = null;
		String actual = serializer.serialize(text);
		assertNull(actual, CommonAssertionMessages.VALUE_NOT_NULL);
	}

	@Test
	void serialize_invalidDouble_text() throws Exception {
		DecimalSerializer serializer = new DecimalSerializer();
		String text = MockFixedValues.SAMPLE_VALUE;
		String expected = text;
		String actual = serializer.serialize(text);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}

	@Test
	void serialize_clean_text() throws Exception {
		DecimalSerializer serializer = new DecimalSerializer();
		String text = "1234.000000000000";
		String expected = "1,234.00";
		String actual = serializer.serialize(text);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}
}
