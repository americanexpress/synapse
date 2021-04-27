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
package com.americanexpress.synapse.client.rest.model;

import java.util.Objects;

/**
 * ClientHeaders class is the model containing the trace and routing for a client.
 *
 * @author Paolo Claudio
 */
public class ClientHeaders {

    /**
     * Trace.
     */
    private ClientTrace trace;

    /**
     * Routing.
     */
    private ClientRouting routing;

    /**
     * Default constructor creates a new instance of ClientHeaders with default values.
     */
    public ClientHeaders() {

        // Fields are set via set methods
    }

    /**
     * Get the trace.
     *
     * @return the trace
     */
    public ClientTrace getTrace() {
        return trace;
    }

    /**
     * Set the trace.
     *
     * @param trace the trace to set
     */
    public void setTrace(ClientTrace trace) {
        this.trace = trace;
    }

    /**
     * Get the routing.
     *
     * @return the routing
     */
    public ClientRouting getRouting() {
        return routing;
    }

    /**
     * Set the routing.
     *
     * @param routing the routing to set
     */
    public void setRouting(ClientRouting routing) {
        this.routing = routing;
    }

    /**
     * Check to see if another ClientHeaders object equals this ClientHeaders object.
     *
     * @param o another ClientHeaders object
     * @return true if and only if another ClientHeaders object equals this ClientHeaders object.
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

        ClientHeaders other = (ClientHeaders) o;
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

    @Override
    public String toString() {
        return "ClientHeaders{" +
                "trace=" + trace +
                ", routing=" + routing +
                '}';
    }
}
