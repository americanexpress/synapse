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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * {@code AllOfValidatorTest} unit test for class {@link AllOfValidator}
 *
 * @author sahilzmaharjan
 */
@ExtendWith(MockitoExtension.class)
class AllOfValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private AllOf allOf;

    private AllOfValidator allOfValidator;

    @BeforeEach
    void setup() {
        when(allOf.fieldNames()).thenReturn(new String[] {"someText1", "someText2"});
        allOfValidator = new AllOfValidator();
        allOfValidator.initialize(allOf);
    }

    @Test
    void isValid_givenNullObject_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        assertFalse(allOfValidator.isValid(null, constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("Invalid configuration for @AllOf annotation. All of the fields must be provided.");
    }

    @Test
    void isValid_givenFieldNamesNull_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(allOf.fieldNames()).thenReturn(new String[] {});
        allOfValidator = new AllOfValidator();
        allOfValidator.initialize(allOf);
        assertFalse(allOfValidator.isValid(new SampleNestedObject(), constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("Invalid configuration for @AllOf annotation. All of the fields must be provided.");
    }

    @Test
    void isValid_givenInvalidFieldNamesNull_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(allOf.fieldNames()).thenReturn(new String[] {"RandomTestField"});
        allOfValidator = new AllOfValidator();
        allOfValidator.initialize(allOf);
        assertFalse(allOfValidator.isValid(new SampleNestedObject(), constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({"test,", ",test", "test,'   '", "'   ',test"})
    void isValid_givenObjectWithOneBlankField_expectedFalse(String value1, String value2) {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(constraintValidatorContext.getDefaultConstraintMessageTemplate()).thenReturn("At least one of the fields %s must be provided.");
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertFalse(allOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

    @ParameterizedTest
    @CsvSource({"test,test1", "someTest,test2", "someTest1,test4", "someTest2,test5"})
    void isValid_givenAllFields_expectedTrue(String value1, String value2) {
        var sampleNestedObject  = new SampleNestedObject();
        sampleNestedObject.setSomeText1(value1);
        sampleNestedObject.setSomeText2(value2);
        assertTrue(allOfValidator.isValid(sampleNestedObject, constraintValidatorContext));
    }

}
