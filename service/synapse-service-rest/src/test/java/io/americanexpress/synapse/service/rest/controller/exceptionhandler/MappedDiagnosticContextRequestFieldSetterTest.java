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
package io.americanexpress.synapse.service.rest.controller.exceptionhandler;

import org.junit.jupiter.api.Test;
import org.slf4j.ext.XLogger;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestClientException;


/**
 * {@code MappedDiagnosticContextRequestFieldSetterTest} tests the {@link MappedDiagnosticContextRequestFieldSetter}
 */
class MappedDiagnosticContextRequestFieldSetterTest {

    private static final RequestPayloadConverter requestPayloadConverter = new RequestPayloadConverter();

    private static final MappedDiagnosticContextRequestFieldSetter MAPPED_DIAGNOSTIC_CONTEXT_REQUEST_FIELD_SETTER = new MappedDiagnosticContextRequestFieldSetter(requestPayloadConverter);

    @Test
    void set_givenMockHttpServletRequestWithAllFields() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setContentType("application-json");
        mockHttpServletRequest.setMethod("post");
        mockHttpServletRequest.setRemoteHost("host");
        mockHttpServletRequest.setRemoteUser("user");
        mockHttpServletRequest.setRequestURI("/id");
        mockHttpServletRequest.setQueryString("id");
        mockHttpServletRequest.addParameter("name", "Synapse");
        MAPPED_DIAGNOSTIC_CONTEXT_REQUEST_FIELD_SETTER.set(XLogger.Level.DEBUG, new RestClientException("Invalid client call"), mockHttpServletRequest);
    }

    @Test
    void set_givenMockHttpServletRequestWithNoFields() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        MAPPED_DIAGNOSTIC_CONTEXT_REQUEST_FIELD_SETTER.set(XLogger.Level.DEBUG, new RestClientException("Invalid client call"), mockHttpServletRequest);
    }
}
