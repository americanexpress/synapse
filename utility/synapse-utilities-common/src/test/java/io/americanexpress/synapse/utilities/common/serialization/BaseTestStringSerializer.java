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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseTestStringSerializer extends BaseTestSerializer {

	@Test
    public void serialize_whiteSpace() throws Exception {
    	arrangeWhiteSpace();
    	String expected = "{\"" + testField + "\":\"\"}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    public abstract void arrangeWhiteSpace();

    @Override
    public String getExpectedEmptyValue() {
    	return "{\"" + testField + "\":\"\"}";
    }
}
