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

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.InvocationTargetException;

/**
 * {@code RequireIfPresentValidator} validates that one field is required if another field is present.
 *
 * @author Breno Pinto
 */
public class RequireIfPresentValidator implements ConstraintValidator<RequireIfPresent, Object> {

    /**
     * Field to check for presence.
     */
    private String field;

    /**
     * Required field that must be present if the field is present.
     */
    private String requiredField;

    /**
     * Initialize the validator in preparation for isValid calls.
     *
     * @param annotation annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(RequireIfPresent annotation) {
        this.field = annotation.field();
        this.requiredField = annotation.requiredField();
    }

    /**
     * Check if the required field is present when the specified field is provided.
     *
     * @param object the object to validate.
     * @param context context in which the constraint is evaluated.
     * @return true if the required field is present when the specified field is provided, false otherwise.
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {

            if (ObjectUtils.isEmpty(object)) {
                setErrorMessage(context, "Invalid configuration for @RequireIfPresent annotation. Both field and requiredField must be provided.");
                return false;
            }

            var fieldValue = PropertyUtils.getProperty(object, field);
            var requiredFieldValue = PropertyUtils.getProperty(object, requiredField);

            if (ObjectUtils.isEmpty(fieldValue) || ObjectUtils.isEmpty(requiredFieldValue)) {
                setErrorMessage(context, "Invalid configuration for @RequireIfPresent annotation. Both field and requiredField must be provided.");
                return false;
            }

            var isFieldPresent = !(fieldValue instanceof String) || StringUtils.isNotBlank((String) fieldValue);
            var isRequiredFieldPresent = !(requiredFieldValue instanceof String) || StringUtils.isNotBlank((String) requiredFieldValue);

            if (isFieldPresent && !isRequiredFieldPresent) {
                setErrorMessage(context, "'%s' is required when '%s' is provided.");
                return false;
            }
            return true;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            setErrorMessage(context, e.getMessage());
            return false;
        }
    }

    /**
     * Set the error message.
     * @param context context in which the constraint is evaluated.
     * @param message error message.
     */
    private void setErrorMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
