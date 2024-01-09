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

/**
 * {@code CheckOnlyOneNotBlankValidator} validates that only one of the fields is not blank.
 * @author Breno Pinto
 */
public class CheckOnlyOneNotBlankValidator implements ConstraintValidator<CheckOnlyOneNotBlank, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(CheckOnlyOneNotBlank constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        if (object == null || ArrayUtils.isEmpty(fieldNames)) {
            return true;
        }
        try {
            var count = 0;
            for (String fieldName:fieldNames){
                var property = PropertyUtils.getProperty(object, fieldName);
                if (property != null) {
                    count++;
                }
            }
            return count == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
