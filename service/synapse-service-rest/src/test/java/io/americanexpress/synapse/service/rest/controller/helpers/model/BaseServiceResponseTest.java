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
package io.americanexpress.synapse.service.rest.controller.helpers.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;

/**
 * {@code BaseServiceResponseTest} for testing purposes
 */
public class BaseServiceResponseTest extends BaseServiceResponse {

    /**
     * value
     */
    private String value;

    /**
     * Constructor takes in value as string and id as string.
     * @param value sets value as a string.
     * @param id sets id as a string.
     */
    public BaseServiceResponseTest(String value, String id) {
        this.value = value;
        this.id = id;
    }

    /**
     * Gets value as string.
     * @return value as string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value with provided string.
     * @param value sets value as string.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns a string format of the object.
     * @return a string of the object.
     */
    @Override
    public String toString() {
        return "BaseServiceResponseTest{" +
                "value='" + value + '\'' +
                '}';
    }
}
