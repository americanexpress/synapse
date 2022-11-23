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
package io.americanexpress.synapse.utilities.common.miscellaneous;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * {@code EmptyObjectValidator} class is used to validate if an object is empty i.e. if all fields of an object are null, then isEmpty(object) will return true.
 *
 * @author Paolo Claudio
 */
public class EmptyObjectValidator {

    /**
     * Check to see if an object is empty i.e. if all fields of an object are full, then this will return true.
     *
     * @param object containing various fields
     * @return true if there exists at least 1 field that is not null; false otherwise
     */
    public static boolean isEmpty(Object object) {

        if (object == null) {
            return true;
        }

        Object fieldValue;
        String stringValue;
        for (Field field : object.getClass().getDeclaredFields()) {

            // Get the raw field's value
            field.setAccessible(true);
            try {
                fieldValue = field.get(object);
            } catch (IllegalArgumentException | IllegalAccessException exception) {
                //This one will be handled by the handlesApplicationException method first if, when cause != null, since cause is the exception that we pass here.
                throw new ApplicationServerException(exception);
            }
            field.setAccessible(false);

            // If the value is a string and the value isn't blank, then this object is not empty
            stringValue = fieldValue == null ? null : fieldValue.toString();
            if (StringUtils.isNotBlank(stringValue)) {
                return false;
            }
        }

        // All of the fields of the object were blank so this object is empty
        return true;
    }
}
