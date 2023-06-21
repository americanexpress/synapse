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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code FixedLengthMaskBigDecimalSerializerTest} tests the {@link FixedLengthMaskBigDecimalSerializer}.
 */
class FixedLengthMaskBigDecimalSerializerTest extends BaseTestStringSerializer {

    @BeforeEach
    @Override
    public void initializeModel() {
        super.initializeModel();
        setTestField("balanceMasking");
    }

    @Override
    public void arrangeWhiteSpace() {
    }

    @Override
    public void arrangeNullField() {
        model.setBalanceMasking(null);
    }

    @Override
    public void arrangeEmptyValue() {

    }

    @Override
    public String getExpectedEmptyValue() {
        return "{}";
    }

    @Test
    @Override
    public void serialize_whiteSpace() throws Exception {
        arrangeWhiteSpace();
        String expected = "{}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }

    @Test
    @Override
    public void serialize_clean() throws Exception {
        model.setBalanceMasking(new BigDecimal(1000000000000.00));
        String expected = "{\"balanceMasking\":\"***\"}";
        String actual = mapper.writeValueAsString(model);
        assertEquals(expected, actual, SERIALIZATION_FAILED);
    }
}
