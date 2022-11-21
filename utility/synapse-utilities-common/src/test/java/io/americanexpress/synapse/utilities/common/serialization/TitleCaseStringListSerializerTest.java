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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code TitleCaseStringListSerializerTest} tests the {@link TitleCaseStringListSerializer}.
 */
class TitleCaseStringListSerializerTest extends BaseTestListStringSerializer {

	@BeforeEach
	@Override
	public void initializeModel() {
		super.initializeModel();
		setTestField("words");
	}

	@Override
	public void arrangeNullField() {
		model.setWords(null);
	}

	@Override
	public void arrangeEmptyValue() {
		model.setWords(new ArrayList<>());
	}

	@Test
	@Override
	public void serialize_clean() throws Exception {
		List<String> words = new ArrayList<>();
		words.add("WALTER KITE");
		words.add("Keisenberg");
		words.add("mr. kite");
		model.setWords(words);
		String expected = "{\"" + testField + "\":[\"Walter Kite\",\"Keisenberg\",\"Mr. Kite\"]}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
	}
}
