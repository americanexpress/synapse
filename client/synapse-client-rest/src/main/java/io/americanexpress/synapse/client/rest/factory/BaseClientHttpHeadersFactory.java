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
package io.americanexpress.synapse.client.rest.factory;

import io.americanexpress.synapse.client.rest.model.BaseClientRequest;
import io.americanexpress.synapse.client.rest.model.ClientHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * {@code BaseClientHttpHeadersFactory} class specifies the prototypes for creating the client HTTP headers for the backend service.
 *
 * @param <I> type of client request
 * @author Paolo Claudio
 */
public abstract class BaseClientHttpHeadersFactory<I extends BaseClientRequest> {

    /**
     * Logger used by all header factories.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(this.getClass());

    /**
     * ObjectMapper used to serialize an object for HMAC generation. This mapper object can be overridden in a child class in their Spring config.
     */
    protected final ObjectMapper mapper;

    /**
     * Initialize the client http headers factory.
     */
    protected BaseClientHttpHeadersFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Prototype for creating the client HTTP headers. The default implementation returns a default HttpHeaders object.
     *
     * @param clientHeaders containing the headers of the request to this API
     * @param request       containing the body of the request to this API
     * @return the client HTTP headers
     */
    public abstract HttpHeaders create(ClientHeaders clientHeaders, I request, String url);
}
