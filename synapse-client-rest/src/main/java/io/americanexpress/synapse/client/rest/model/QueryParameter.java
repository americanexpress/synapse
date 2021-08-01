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
 * QueryParameter is used to hold the key and value pair of a query parameter so that it can be added to the URI.
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QueryParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String formattedQueryParameter(String key, String value) {
        return key + "=" + value;
    }

}
