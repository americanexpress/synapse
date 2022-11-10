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
package io.americanexpress.synapse.client.rest.model;

import io.americanexpress.synapse.utilities.common.model.BaseModel;
import com.openpojo.business.annotation.BusinessKey;

/**
 * {@code QueryParameter} is used to hold the key and value pair of a query parameter so that it can be added to the URI.
 *
 * @author Christie Goldstein
 */
public class QueryParameter extends BaseModel {

    /**
     * Key
     */
    @BusinessKey
    private String key;

    /**
     * Value
     */
    @BusinessKey
    private String value;

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Instantiates a new Query parameter.
     *
     * @param key   the key
     * @param value the value
     */
    public QueryParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Format string.
     *
     * @return the string
     */
    public String format() {
        return key + "=" + value;
    }

}
