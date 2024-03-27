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
package io.americanexpress.synapse.utilities.common.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailAddressUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"test@synapse.com", "TEST@DOMAIN.COM", "123test789TEST@TEST.COM"})
    void isValid_givenValidEmailAddress_expectedTrue(String email) {
        Assertions.assertTrue(EmailAddressUtils.isValid(email));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "test.com", "test@com", "test@.com", "test@com."})
    void isValid_givenInvalidEmailAddress_expectedFalse(String email) {
        Assertions.assertFalse(EmailAddressUtils.isValid(email));
    }

}
