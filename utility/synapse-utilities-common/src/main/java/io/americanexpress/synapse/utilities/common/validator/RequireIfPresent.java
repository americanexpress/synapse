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

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code RequireIfPresent} validates that one field is required if another field is present.
 *
 * @author Breno Pinto
 */
@Documented
@Constraint(validatedBy = RequireIfPresentValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireIfPresent {

    /**
     * Message to be returned when validation fails.
     *
     * @return the validation error message.
     */
    String message() default "Field '%s' is required when field '%s' is present.";

    /**
     * Groups for the constraint.
     *
     * @return the groups for the constraint.
     */
    Class<?>[] groups() default {};

    /**
     * Payload for the constraint.
     *
     * @return the payload for the constraint.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Field to check for presence.
     *
     * @return the field to check for presence.
     */
    String field();

    /**
     * Required field that must be present if the field is present.
     *
     * @return the required field that must be present.
     */
    String requiredField();
}
