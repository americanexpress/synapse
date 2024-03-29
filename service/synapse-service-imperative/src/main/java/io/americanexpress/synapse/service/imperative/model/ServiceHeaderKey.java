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
package io.americanexpress.synapse.service.imperative.model;

/**
 * {@code ServiceHeaderKey} enum will hold all of the key values for the service headers.
 *
 */
public enum ServiceHeaderKey {

    /**
     * Authorization.
     */
    AUTHORIZATION("Authorization"),

    /**
     * Client identifier.
     */
    CLIENT_ID("Client-ID"),

    /**
     * Client version.
     */
    CLIENT_VERSION("Client-Version"),

    /**
     * Correlation Identifier Key.
     */
    CORRELATION_IDENTIFIER_KEY("Correlation-ID"),

    /**
     * Use Case Name Key.
     */
    USE_CASE_NAME_KEY("Journey-ID");

    /**
     * Value/name of the enum.
     */
    private String value;

    /**
     * Constructor which sets the default value of the enum.
     *
     * @param value actual name of the enum.
     */
    ServiceHeaderKey(String value) {
        this.value = value;
    }

    /**
     * Gets the value/name of the enum.
     *
     * @return the name of the enum to be returned.
     */
    public String getValue() {
        return value;
    }
}
