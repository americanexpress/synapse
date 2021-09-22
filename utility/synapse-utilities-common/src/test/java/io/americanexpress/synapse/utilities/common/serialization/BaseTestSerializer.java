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

import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import io.americanexpress.synapse.utilities.common.serialization.model.SampleModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UtilitiesCommonConfig.class)
public abstract class BaseTestSerializer {

	protected final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

	@Autowired
    protected ObjectMapper mapper;

	protected SampleModel model;

	protected String testField;

	protected static final String SERIALIZATION_EXPECTED_NULL = "Serialization expected null value for its field.";

	protected static final String SERIALIZATION_EXPECTED_EMPTY = "Serialization expected empty value.";

	protected static final String SERIALIZATION_FAILED = "Serialization failed.";

	protected static final String NONEXISTENT_FIELD_NAME = "nonexistent";

	public BaseTestSerializer() {
		setTestField(NONEXISTENT_FIELD_NAME);
	}

	protected void setModel(SampleModel model) {
		this.model = model;
	}

	protected void setTestField(String testField) {
		this.testField = testField;
	}

    @BeforeEach
    public void initializeModel() {
    	setModel(new SampleModel());
    }

    @Test
    public void serialize_nullModel() throws Exception {
    	SampleModel model = null;
    	String expected = "null";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_EXPECTED_NULL);
    }

    @Test
    public void serialize_emptyModel() throws Exception {
    	String expected = "{}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_EXPECTED_EMPTY);
    }

    @Test
    public void serialize_nullField() throws Exception {
    	arrangeNullField();
    	String expected = "{}";
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_EXPECTED_EMPTY);
    }

    public abstract void arrangeNullField();

    @Test
    public void serialize_emptyValue() throws Exception {
    	arrangeEmptyValue();
    	String expected = getExpectedEmptyValue();
		String actual = mapper.writeValueAsString(model);
		assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    public abstract void arrangeEmptyValue();

    public abstract String getExpectedEmptyValue();

    public abstract void serialize_clean() throws Exception;
}
