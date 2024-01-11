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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * {@code OneOfValidator} validates that only one of the fields is provided.
 *
 * @author Breno Pinto
 */
public class OneOfValidator implements ConstraintValidator<OneOf, Object> {

    /**
     * Fields to be validated.
     */
    private String[] fieldNames;

    /**
     * Initialize the validator in preparation for isValid calls.
     * @param constraintAnnotation annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(OneOf constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    /**
     * Check if one and only one of the fields is provided.
     * @param object object to validate.
     * @param context context in which the constraint is evaluated.
     * @return true if only one of the fields is provided.
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        if (object == null || ArrayUtils.isEmpty(fieldNames)) {
            return true;
        }
        try {
            var isOneOf = false;
            for (String fieldName : fieldNames) {
                var property = PropertyUtils.getProperty(object, fieldName);
                if (property != null) {
                    if (isOneOf) {
                        return false;
                    }
                    isOneOf = true;
                }
            }
            return isOneOf;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }
}
