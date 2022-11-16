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
        mockHttpServletRequest.setRequestURI("/books");
        mockHttpServletRequest.setQueryString("id");
        mockHttpServletRequest.addParameter("title", "Alice In Wonderland");
        MAPPED_DIAGNOSTIC_CONTEXT_REQUEST_FIELD_SETTER.set(XLogger.Level.DEBUG, new RestClientException("Invalid client call"), mockHttpServletRequest);
    }

    @Test
    void set_givenMockHttpServletRequestWithNoFields() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        MAPPED_DIAGNOSTIC_CONTEXT_REQUEST_FIELD_SETTER.set(XLogger.Level.DEBUG, new RestClientException("Invalid client call"), mockHttpServletRequest);
    }
}
