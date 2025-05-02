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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * {@code AllOfValidator} validates that all the fields are provided.
 *
 * @author sahilzmaharjan
 */
public class AllOfValidator implements ConstraintValidator<AllOf, Object> {

    /**
     * Fields to be validated.
     */
    private String[] fieldNames;

    /**
     * Initialize the validator in preparation for isValid calls.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(AllOf constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    /**
     * Check if all the fields are provided.
     *
     * @param object object to validate.
     * @param constraintValidatorContext context in which constraint is evaluated.
     * @return true if all the fields is provided.
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if(object == null || ArrayUtils.isEmpty(fieldNames)) {
            this.setErrorMessage(constraintValidatorContext, "Invalid configuration for @AllOf annotation. All of the fields must be provided.");
            return false;
        }
        for(String fieldName: fieldNames) {
            try {
                var property = PropertyUtils.getProperty(object, fieldName);
                if(ObjectUtils.isEmpty(property) || (!property.getClass().equals(String.class) || StringUtils.isBlank((String) property))) {
                    this.setErrorMessage(constraintValidatorContext, String.format(constraintValidatorContext.getDefaultConstraintMessageTemplate(), Arrays.toString(this.fieldNames)));
                    return false;
                }
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                this.setErrorMessage(constraintValidatorContext, e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Set the error message.
     *
     * @param constraintValidatorContext context in which the constraint is evaluated.
     * @param message error message.
     */
    private void setErrorMessage(ConstraintValidatorContext constraintValidatorContext, String message) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
