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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
class EmailAddressValidatorTest {

    private final EmailAddress emailAddress = Mockito.mock(EmailAddress.class);

    private final ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);

    private final EmailAddressValidator emailAddressValidator = new EmailAddressValidator();

    @BeforeEach
    void init() {
        emailAddressValidator.initialize(emailAddress);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"    ", "email@synapse.com", "EMAIL@SYNAPSE.COM", "email1234@SYNAPSE.COM"})
    void isValid_givenValidEmailAddress_expectedTrue(String email) {
        var testModel = new TestModel();
        testModel.setEmail(email);

        boolean actual = emailAddressValidator.isValid(testModel.getEmail(), constraintValidatorContext);
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"EMAIL@SYNAPSE", "email1234@.COM"})
    void isValid_givenInvalidEmailAddress_expectedFalse(String email) {
        var testModel = new TestModel();
        testModel.setEmail(email);

        boolean actual = emailAddressValidator.isValid(testModel.getEmail(), constraintValidatorContext);
        Assertions.assertFalse(actual);
    }
}
