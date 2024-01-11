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
package io.americanexpress.synapse.utilities.common.validator;

import io.americanexpress.synapse.utilities.common.model.SampleNestedObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.ConstraintValidatorContext;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * {@code OneOfValidatorTest} unit test class for {@link OneOfValidator}.
 *
 * @author Breno Pinto
 */
@ExtendWith(MockitoExtension .class)
class OneOfValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;
    @Mock
    private OneOf oneOf;

    private OneOfValidator oneOfValidator;

    @BeforeEach
    void setup() {
        when(oneOf.fieldNames()).thenReturn(new String[] {"someText1", "someText2"});
        oneOfValidator = new OneOfValidator();
        oneOfValidator.initialize(oneOf);
    }
    @Test
    void isValid_givenNullObject_expectedTrue() {
        assertTrue(oneOfValidator.isValid(null, constraintValidatorContext));
    }

    @Test
    void isValid_givenFieldNamesNull_expectedTrue() {
        when(oneOf.fieldNames()).thenReturn(new String[] {});
        oneOfValidator = new OneOfValidator();
        oneOfValidator.initialize(oneOf);
        assertTrue(oneOfValidator.isValid(new SampleNestedObject(), constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({"test,", ",test", "test,   ", "   ,test"})
    void isValid_givenObjectWithOneNotBlankField_expectedTrue(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertTrue(oneOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({",", "   ,   ", "test,test"})
    void isInvalid_givenObjectWithInvalidFields_expectedFalse(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertFalse(oneOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }
}
