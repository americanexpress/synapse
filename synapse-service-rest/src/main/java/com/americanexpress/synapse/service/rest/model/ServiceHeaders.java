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
package com.americanexpress.synapse.service.rest.model;

import java.util.Objects;

/**
 * ServiceHeaders class contains the header information received from the consumer.
 *
 * @author Paolo Claudio
 */
public class ServiceHeaders {

    /**
     * Trace elements.
     */
    private ServiceTrace trace;

    /**
     * Routing elements.
     */
    private ServiceRouting routing;

    /**
     * Default constructor creates a new instance of ServiceHeaders with default values.
     */
    public ServiceHeaders() {
        // Fields are set via set methods
    }

    /**
     * Get the trace.
     *
     * @return the trace
     */
    public ServiceTrace getTrace() {
        return trace;
    }

    /**
     * Set the trace.
     *
     * @param trace the trace to set
     */
    public void setTrace(ServiceTrace trace) {
        this.trace = trace;
    }

    /**
     * Get the routing.
     *
     * @return the routing
     */
    public ServiceRouting getRouting() {
        return routing;
    }

    /**
     * Set the routing.
     *
     * @param routing the routing to set
     */
    public void setRouting(ServiceRouting routing) {
        this.routing = routing;
    }

    /**
     * Check to see if another ServiceHeaders object equals this ServiceHeaders object.
     *
     * @param o another ServiceHeaders object
     * @return true if and only if another ServiceHeaders object equals this ServiceHeaders object.
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        ServiceHeaders other = (ServiceHeaders) o;
        return Objects.equals(routing, other.routing)
                && Objects.equals(trace, other.trace);
    }

    /**
     * Return the hash code of this object.
     *
     * @return hash code of this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(routing, trace);
    }

    /**
     * Return the string representation of this object.
     *
     * @return the string representation of this object
     */
    @Override
    public String toString() {
        return "ServiceHeaders{" +
                "trace=" + trace +
                ", routing=" + routing +
                '}';
    }
}
