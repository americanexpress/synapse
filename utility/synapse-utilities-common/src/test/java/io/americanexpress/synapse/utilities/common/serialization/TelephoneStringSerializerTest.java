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
 * {@code TelephoneStringSerializerTest} tests the {@link TelephoneStringSerializer}.
 */
class TelephoneStringSerializerTest extends BaseTestStringSerializer {

	@BeforeEach
	@Override
	public void initializeModel() {
		super.initializeModel();
		setTestField("phoneNumber");
	}

	@Override
	public void arrangeNullField() {
		model.setPhoneNumber(null);
	}

	@Override
	public void arrangeEmptyValue() {
		model.setPhoneNumber("");
	}

	@Override
	public void arrangeWhiteSpace() {
		model.setPhoneNumber(" ");
	}

	@Test
	void serialize_anyString() throws Exception {
		model.setPhoneNumber("a");
		String expected = "{\"" + testField + "\":\"a\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
	}

	@Test
	void serialize_length10String() throws Exception {
		model.setPhoneNumber("abcdefghij");
		String expected = "{\"" + testField + "\":\"abcdefghij\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
	}

	@Test
	void serialize_length10StringUntrimmed() throws Exception {
		model.setPhoneNumber(" abcdefghij ");
		String expected = "{\"" + testField + "\":\"abcdefghij\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
	}

	@Test
	void serialize_validPhoneNumberUntrimmed() throws Exception {
		model.setPhoneNumber(" 1234567890 ");
		String expected = "{\"" + testField + "\":\"(123) 456-7890\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
	}

	@Test
	@Override
	public void serialize_clean() throws Exception {
		model.setPhoneNumber("1234567890");
		String expected = "{\"" + testField + "\":\"(123) 456-7890\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected well formatted phone number.");
	}
}
