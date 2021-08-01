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
package io.americanexpress.synapse.service.rest.model;

/**
 * <code>ClientHeaderValue</code> enum is used to house all the header values for the clients.
 *
 */
public enum ServiceHeaderValue {

    CORRELATION_IDENTIFIER_VALUE("1234"),
    CLIENT_IDENTIFIER_VALUE("AppName");

    /**
     * The value of the enum.
     */
    private String value;

    /**
     * The constructor that sets the default value of the enum.
     *
     * @param value value of the enum to set.
     */
    ServiceHeaderValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the enum.
     *
     * @return the value of the enum to return.
     */
    public String getValue() {
        return value;
    }


}
