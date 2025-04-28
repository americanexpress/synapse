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
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * {@code AnyOfValidatorTest} unit test class for {@link AnyOfValidator}.
 *
 * @author sahilzmaharjan
 */
@ExtendWith(MockitoExtension.class)
class AnyOfValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private AnyOf anyOf;

    private AnyOfValidator anyOfValidator;

    @BeforeEach
    void setup() {
        when(anyOf.fieldNames()).thenReturn(new String[] {"someText1", "someText2"});
        anyOfValidator = new AnyOfValidator();
        anyOfValidator.initialize(anyOf);
    }

    @Test
    void isValid_givenNullObject_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        assertFalse(anyOfValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("Invalid configuration for @AnyOf annotation. At least one field must be provided.");
    }

    @Test
    void isValid_givenFieldNamesNull_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(anyOf.fieldNames()).thenReturn(new String[] {});
        anyOfValidator = new AnyOfValidator();
        anyOfValidator.initialize(anyOf);
        assertFalse(anyOfValidator.isValid(new SampleNestedObject(), constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("Invalid configuration for @AnyOf annotation. At least one field must be provided.");
    }

    @Test
    void isValid_givenInvalidMethod_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(constraintValidatorContext.getDefaultConstraintMessageTemplate()).thenReturn("At least one of the fields %s must be provided.");
        when(anyOf.fieldNames()).thenReturn(new String[] {"randomTestField"});
        anyOfValidator = new AnyOfValidator();
        anyOfValidator.initialize(anyOf);
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1("someText");
        assertFalse(anyOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({"test,", ",test", "test,'   '", "'   ',test"})
    void isValid_givenObjectWithOneNotBlankField_expectedTrue(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertTrue(anyOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({",", "'   ','   '", "test,test", "'',''", ",''"})
    void isInvalid_givenObjectWithInvalidFields_expectedFalse(String value1, String value2) {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(constraintValidatorContext.getDefaultConstraintMessageTemplate()).thenReturn("At least one of the fields %s must be provided.");
        var sampleNestedObject  = new SampleNestedObject();
        assertFalse(anyOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("At least one of the fields [someText1, someText2] must be provided.");
    }

}
