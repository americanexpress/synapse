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

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * {@code ServiceTrace} class contains the trace elements received from the consumer.
 *
 * @author Paolo Claudio
 */
public class ServiceTrace {

    /**
     * Correlation ID.
     */
    private String correlationId;

    /**
     * Default constructor creates a new instance of ServiceTrace with default values.
     */
    public ServiceTrace() {

        // Fields are set via set methods
    }

    /**
     * Get the correlationId.
     *
     * @return the correlationId
     */
    @ApiModelProperty(value = "This is a unique identifier used for operational logging purposes")
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Set the correlationId.
     *
     * @param correlationId the correlationId to set
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    /**
     * Return the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return "ServiceTrace{" +
                "correlationId='" + correlationId + '\'' +
                '}';
    }

    /**
     * Equals.
     * @param o an object
     * @return a boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceTrace that = (ServiceTrace) o;

        return Objects.equals(correlationId, that.correlationId);
    }

    /**
     * hashCode.
     * @return an integer hash
     */
    @Override
    public int hashCode() {
        return correlationId != null ? correlationId.hashCode() : 0;
    }
}
