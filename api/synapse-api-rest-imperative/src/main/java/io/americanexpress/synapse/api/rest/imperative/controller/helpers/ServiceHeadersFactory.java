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
package io.americanexpress.synapse.api.rest.imperative.controller.helpers;

import io.americanexpress.synapse.service.imperative.model.ServiceHeaderKey;
import io.americanexpress.synapse.service.imperative.model.ServiceHeaders;
import io.americanexpress.synapse.service.imperative.model.ServiceRouting;
import io.americanexpress.synapse.service.imperative.model.ServiceTrace;
import org.springframework.http.HttpHeaders;

/**
 * {@code ServiceHeadersFactory} class produces ServiceHeaders objects.
 *
 * @author Paolo Claudio
 */
public class ServiceHeadersFactory {

    /**
     * Default constructor.
     */
    private ServiceHeadersFactory() {
    }

    /**
     * Create a ServiceHeaders object given HTTP headers.
     *
     * @param headers containing the HTTP headers
     * @return the ServiceHeaders object
     */
    public static ServiceHeaders create(HttpHeaders headers) {
        ServiceTrace trace = new ServiceTrace();
        trace.setCorrelationId(headers.getFirst(ServiceHeaderKey.CORRELATION_IDENTIFIER_KEY.getValue()));

        ServiceRouting routing = new ServiceRouting();
        ServiceHeaders serviceHeaders = new ServiceHeaders();
        serviceHeaders.setTrace(trace);
        serviceHeaders.setRouting(routing);

        return serviceHeaders;
    }
}
