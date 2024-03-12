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

import io.americanexpress.synapse.utilities.common.email.EmailAddressUtils;
import org.apache.commons.lang3.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@code EmailAddressValidator} is used for constraining {@link EmailAddress} annotation.
 */
public class EmailAddressValidator implements ConstraintValidator<EmailAddress, String> {

    /**
     * Checks if an email address is valid.
     *
     * @param emailAddress the email address
     * @return true if email address is valid
     */
    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotBlank(emailAddress) ? EmailAddressUtils.isValid(emailAddress) : true;
    }

}
