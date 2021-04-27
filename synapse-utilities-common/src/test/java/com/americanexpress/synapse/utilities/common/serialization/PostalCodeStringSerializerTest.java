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

public class PostalCodeStringSerializerTest extends BaseTestStringSerializer {

	@BeforeEach
	@Override
	public void initializeModel() {
		super.initializeModel();
		setTestField("zip_code");
	}

	@Override
	public void arrangeNullField() {
		model.setZipCode(null);
	}

	@Override
	public void arrangeEmptyValue() {
		model.setZipCode("");
	}

	@Override
	public void arrangeWhiteSpace() {
		model.setZipCode(" ");
	}

	@Test
	public void serialize_anyString() throws Exception {
		model.setZipCode("a");
		String expected = "{\"" + testField + "\":\"a\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected different result.");
	}

	@Test
	public void serialize_length9String() throws Exception {
		model.setZipCode("abcdefghi");
		String expected = "{\"" + testField + "\":\"abcdefghi\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected different result.");
	}

	@Test
	public void serialize_length9StringUntrimmed() throws Exception {
		model.setZipCode(" abcdefghi ");
		String expected = "{\"" + testField + "\":\"abcdefghi\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected different result.");
	}

	@Test
	public void serialize_validZipCodeUntrimmed() throws Exception {
		model.setZipCode(" 123456789 ");
		String expected = "{\"" + testField + "\":\"12345-6789\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected different result.");
	}

	@Test
	@Override
	public void serialize_clean() throws Exception {
		model.setZipCode("123456789");
		String expected = "{\"" + testField + "\":\"12345-6789\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, "Serialization expected well formatted zip code.");
	}
}
