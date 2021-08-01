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

import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import io.americanexpress.synapse.framework.test.MockFixedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class CurrencySerializerTest extends BaseTestSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("currency_amount");
        Locale.setDefault(Locale.US);
    }

    @Override
    public void arrangeNullField() {
        model.setCurrencyAmount(null);
    }

    @Override
    public void arrangeEmptyValue() {
        model.setCurrencyAmount(new BigDecimal("0"));
    }

    @Override
    public String getExpectedEmptyValue() {
        return "{\"currency_amount\":\"$0.00\"}";
    }

    @Test
    void serialize_zeroValue() throws Exception {
        model.setCurrencyAmount(new BigDecimal("0"));
        String expected = "{\"currency_amount\":\"$0.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_negativeIntegersOnly() throws Exception {
        model.setCurrencyAmount(new BigDecimal("-1234"));
        String expected = "{\"currency_amount\":\"-$1,234.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_integersOnly() throws Exception {
        model.setCurrencyAmount(new BigDecimal("1000"));
        String expected = "{\"currency_amount\":\"$1,000.00\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_negativeMultipleDecimalPlaces() throws Exception {
        model.setCurrencyAmount(new BigDecimal("-1234.444"));
        String expected = "{\"currency_amount\":\"-$1,234.44\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_multipleDecimalPlaces() throws Exception {
        model.setCurrencyAmount(new BigDecimal("1000.2388"));
        String expected = "{\"currency_amount\":\"$1,000.24\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        model.setCurrencyAmount(new BigDecimal("123456.1234"));
        String expected = "{\"currency_amount\":\"$123,456.12\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual);
    }

    @Test
    void serialize_nullString_text() throws Exception {
        CurrencySerializer serializer = new CurrencySerializer();
        String text = null;
        String actual = serializer.serialize(text);
        assertNull(actual, CommonAssertionMessages.VALUE_NOT_NULL);
    }

    @Test
    void serialize_invalidBigDecimal_text() throws Exception {
        CurrencySerializer serializer = new CurrencySerializer();
        String text = MockFixedValues.SAMPLE_VALUE;
        String expected = text;
        String actual = serializer.serialize(text);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }

    @Test
    void serialize_clean_text() throws Exception {
        CurrencySerializer serializer = new CurrencySerializer();
        String text = "123456.1234";
        String expected = "$123,456.12";
        String actual = serializer.serialize(text);
        assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
    }
}
