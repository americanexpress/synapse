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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code PercentSerializerTest} tests the {@link PercentSerializer}.
 */
class PercentSerializerTest extends BaseTestSerializer {

	@BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("odds");
        Locale.setDefault(Locale.US);
    }

	@Override
	public void arrangeNullField() {
		model.setOdds(null);
	}

	@Override
	public void arrangeEmptyValue() {
		model.setOdds(null);
	}

	@Override
	public String getExpectedEmptyValue() {
		return "{}";
	}

	@Test
	void serialize_zeroValue() throws Exception {
		model.setOdds(0.00);
		String expected = "{\"odds\":\"0.00%\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual);
	}

	@Test
	void serialize_negativePercentage() throws Exception {
		model.setOdds(-0.12);
		String expected = "{\"odds\":\"-12.00%\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual);
	}

	@Test
	void serialize_oneHundredPercent() throws Exception {
		model.setOdds(1.0);
		String expected = "{\"odds\":\"100.00%\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual);
	}

	@Test
	@Override
	public void serialize_clean() throws Exception {
		model.setOdds(0.335566);
		String expected = "{\"odds\":\"33.56%\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual);
	}

	@Test
	void serialize_nullString_text() {
		PercentSerializer serializer = new PercentSerializer();
		String text = null;
		String actual = serializer.serialize(text);
		Assertions.assertNull(actual, CommonAssertionMessages.VALUE_NOT_NULL);
	}

	@Test
	void serialize_invalidDouble_text() {
		PercentSerializer serializer = new PercentSerializer();
		String text = MockFixedValues.SAMPLE_VALUE;
		String expected = text;
		String actual = serializer.serialize(text);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}

	@Test
	void serialize_clean_text() {
		PercentSerializer serializer = new PercentSerializer();
		String text = "0.335566";
		String expected = "33.56%";
		String actual = serializer.serialize(text);
		assertEquals(expected, actual, CommonAssertionMessages.VALUE_NOT_EQUAL);
	}
}
