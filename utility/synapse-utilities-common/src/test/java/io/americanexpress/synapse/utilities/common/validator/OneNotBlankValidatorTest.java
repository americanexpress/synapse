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
 * {@code OneNotBlankValidatorTest} unit test class for {@link OneNotBlankValidator}.
 *
 * @author Breno Pinto
 */
@ExtendWith(MockitoExtension .class)
class OneNotBlankValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;
    @Mock
    private OneNotBlank oneNotBlank;

    private OneNotBlankValidator oneNotBlankValidator;

    @BeforeEach
    void setup() {
        when(oneNotBlank.fieldNames()).thenReturn(new String[] {"someText1", "someText2"});
        oneNotBlankValidator = new OneNotBlankValidator();
        oneNotBlankValidator.initialize(oneNotBlank);
    }
    @Test
    void isValid_givenNullObject_expectedTrue() {
        assertTrue(oneNotBlankValidator.isValid(null, constraintValidatorContext));
    }

    @Test
    void isValid_givenFieldNamesNull_expectedTrue() {
        when(oneNotBlank.fieldNames()).thenReturn(new String[] {});
        oneNotBlankValidator = new OneNotBlankValidator();
        oneNotBlankValidator.initialize(oneNotBlank);
        assertTrue(oneNotBlankValidator.isValid(new SampleNestedObject(), constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({"test,", ",test", "test,   ", "   ,test"})
    void isValid_givenObjectWithOneNotBlankField_expectedTrue(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertTrue(oneNotBlankValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({",", "   ,   ", "test,test"})
    void isInvalid_givenObjectWithInvalidFields_expectedFalse(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertFalse(oneNotBlankValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }
}
