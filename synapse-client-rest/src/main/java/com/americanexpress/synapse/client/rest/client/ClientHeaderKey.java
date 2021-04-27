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
package com.americanexpress.synapse.client.rest.client;

/**
 * <code>ClientHeaderKey</code> enum will house all the header keys.
 *
 * @author Gabriel Jimenez
 */
public enum ClientHeaderKey {

    // Used in all api headers.
    CORRELATION_IDENTIFIER_KEY("Correlation-ID");

    /**
     * The value of the enum.
     */
    private String value;

    /**
     * Constructor which sets the default value of the enum.
     *
     * @param value the value of the enum to set.
     */
    ClientHeaderKey(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the enum.
     *
     * @return the value of the enum.
     */
    public String getValue() {
        return value;
    }
}
