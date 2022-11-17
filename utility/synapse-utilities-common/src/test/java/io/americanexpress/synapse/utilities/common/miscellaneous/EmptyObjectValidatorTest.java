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
package io.americanexpress.synapse.utilities.common.miscellaneous;

import io.americanexpress.synapse.utilities.common.serialization.model.SampleModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * {@code EmptyObjectValidatorTest} tests the {@link EmptyObjectValidator}.
 */
class EmptyObjectValidatorTest {

    private static final String OBJECT_EMPTY = "The object is empty.";

    private static final String OBJECT_NOT_EMPTY = "The object is not empty.";

    @Test
    void isEmptyObject_null() {
        boolean actual = EmptyObjectValidator.isEmpty(null);
        assertTrue(actual, OBJECT_EMPTY);
    }


    @Test
    void isEmptyObject_sampleModelEmpty() {
        SampleModel sampleModel = new SampleModel();
        boolean actual = EmptyObjectValidator.isEmpty(sampleModel);
        assertTrue(actual, OBJECT_EMPTY);
    }

    @Test
    void isEmptyObject_sampleModelString() {
        SampleModel sampleModel = new SampleModel();
        sampleModel.setFirstName("Christie");
        boolean actual = EmptyObjectValidator.isEmpty(sampleModel);
        assertFalse(actual, OBJECT_NOT_EMPTY);
    }

    @Test
    void isEmptyObject_sampleModelBigDecimal() {
        SampleModel sampleModel = new SampleModel();
        sampleModel.setBalanceMasking(BigDecimal.valueOf(100.0));
        boolean actual = EmptyObjectValidator.isEmpty(sampleModel);
        assertFalse(actual, OBJECT_NOT_EMPTY);
    }
}



