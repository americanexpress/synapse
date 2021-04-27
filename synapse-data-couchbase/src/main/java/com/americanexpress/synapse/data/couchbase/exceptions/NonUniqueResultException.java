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
package com.americanexpress.synapse.data.couchbase.exceptions;


import com.americanexpress.synapse.data.couchbase.parameter.Parameters;

/**
 * The type Non unique result exception.
 */
public class NonUniqueResultException extends RuntimeException {
    /**
     * Instantiates a new Non unique result exception.
     *
     * @param params the params
     */
    public NonUniqueResultException(Parameters params) {
        super("More than one document found with params: " + params.toJsonArray().toString());
    }
}
