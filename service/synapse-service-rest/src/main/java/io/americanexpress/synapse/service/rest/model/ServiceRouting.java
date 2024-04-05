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

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * {@code ServiceRouting} class contains the routing elements received from the consumer.
 *
 * @author Paolo Claudio
 */
public class ServiceRouting {

    /**
     * Client Identifier.
     */
    private String clientId;

    /**
     * Default constructor creates a new instance of ServiceRouting with default values.
     */
    public ServiceRouting() {

        // Fields are set via set methods
    }


    /**
     * Gets the client identifier.
     *
     * @return the client identifier.
     */
    @Schema(description = "Unique client id assigned to the consumer")
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the client identifier.
     *
     * @param clientId the client identifier to set.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceRouting that = (ServiceRouting) o;

        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return clientId != null ? clientId.hashCode() : 0;
    }

    /**
     * Return the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return "ServiceRouting{" +
                "clientId='" + clientId + '\'' +
                '}';
    }
}
