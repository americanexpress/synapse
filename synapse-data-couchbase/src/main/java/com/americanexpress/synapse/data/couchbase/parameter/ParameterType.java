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
package com.americanexpress.synapse.data.couchbase.parameter;

/**
 * The enum Parameter type.
 */
public enum ParameterType {
    /**
     * Equals parameter type.
     */
    EQUALS,
    /**
     * Not equals parameter type.
     */
    NOT_EQUALS,
    /**
     * Contains parameter type.
     */
    CONTAINS,
    /**
     * In parameter type.
     */
    IN,
    /**
     * Greater than or equals parameter type.
     */
    GREATER_THAN_OR_EQUALS,
    /**
     * Less than or equals parameter type.
     */
    LESS_THAN_OR_EQUALS,
    /**
     * Is null parameter type.
     */
    IS_NULL,
    /**
     * Is not null parameter type.
     */
    IS_NOT_NULL,
    /**
     * Is missing parameter type.
     */
    IS_MISSING,
    /**
     * Is missing parameter type.
     */
    IS_NOT_MISSING,
    /**
     * Is missing or null parameter type.
     */
    IS_MISSING_OR_NULL, 
    /**
     * Between greater than or equals parameter type 
     */
    BETWEEN_START,
    /**
     * Between less than or equals parameter type 
     */
    BETWEEN_END

}
