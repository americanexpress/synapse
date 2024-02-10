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

import java.util.Map;

/**
 * {@code BaseServiceRequest} specifies the prototypes for all service requests.
 *
 * @author Francois Gutt
 */
public abstract class BaseServiceRequest {

    /**
     * The service header map.
     */
    private Map<String, String> serviceHeaders;

    /**
     * Get the service headers map.
     *
     * @return a map of service headers
     */
    public Map<String, String> getServiceHeaders() {
        return serviceHeaders;
    }

    /**
     * Sets the service header map.
     *
     * @param serviceHeaders a map of service headers.
     */
    public void setServiceHeaders(Map<String, String> serviceHeaders) {
        this.serviceHeaders = serviceHeaders;
    }
}