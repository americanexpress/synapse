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

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ServiceRouting class contains the routing elements received from the consumer.
 *
 * @author Paolo Claudio
 */
public class ServiceRouting {

    /**
     * Client Identifier.
     */
    private String clientIdentifier;

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
    @ApiModelProperty(value = "Unique client identifier assigned to the consumer")
    public String getClientIdentifier() {
        return clientIdentifier;
    }

    /**
     * Sets the client identifier.
     *
     * @param clientIdentifier the client identifier to set.
     */
    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceRouting that = (ServiceRouting) o;

        return Objects.equals(clientIdentifier, that.clientIdentifier);
    }

    @Override
    public int hashCode() {
        return clientIdentifier != null ? clientIdentifier.hashCode() : 0;
    }

    /**
     * Return the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return "ServiceRouting{" +
                "clientIdentifier='" + clientIdentifier + '\'' +
                '}';
    }
}
