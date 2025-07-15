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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@code RequireIfPresentValidatorTest} unit test class for {@link RequireIfPresentValidator}.
 *
 * @author Breno Pinto
 */
@ExtendWith(MockitoExtension.class)
class RequireIfPresentValidatorTest {

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private RequireIfPresent requireIfPresent;

    private RequireIfPresentValidator requireIfPresentValidator;

    @BeforeEach
    void setup() {
        when(requireIfPresent.field()).thenReturn("someText1");
        when(requireIfPresent.requiredField()).thenReturn("someText2");
        requireIfPresentValidator = new RequireIfPresentValidator();
        requireIfPresentValidator.initialize(requireIfPresent);
    }

    @Test
    void isValid_givenFieldAndRequiredField_expectedTrue() {
        var object = new SampleNestedObject();
        object.setSomeText1("field");
        object.setSomeText2("requiredField");

        assertTrue(requireIfPresentValidator.isValid(object, constraintValidatorContext));
    }

    @Test
    void isValid_givenEmptyFieldAndRequiredField_expectedTrue() {
        var object = new SampleNestedObject();
        object.setSomeText1("");
        object.setSomeText2("requiredField");

        assertTrue(requireIfPresentValidator.isValid(object, constraintValidatorContext));
    }

    @Test
    void isValid_givenFieldAndEmptyRequiredField_expectedFalse() {
        var builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        var object = new SampleNestedObject();
        object.setSomeText1("field");
        object.setSomeText2("");

        assertFalse(requireIfPresentValidator.isValid(object, constraintValidatorContext));
        verify(constraintValidatorContext).buildConstraintViolationWithTemplate("someText2 is required when someText1 is provided.");
    }
}
