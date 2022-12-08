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

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * {@code InternalServerErrorResponseCreatorTest} tests the {@link InternalServerErrorResponseCreator}
 */
@ExtendWith(MockitoExtension.class)
class InternalServerErrorResponseCreatorTest {

    @Mock
    private ErrorMessagePropertyReader messagePropertyReader;

    @Spy
    private final RequestPayloadConverter requestPayLoadToStringConverter = new RequestPayloadConverter();

    @Spy
    private final MappedDiagnosticContextRequestFieldSetter mappedDiagnosticContextRequestFieldSetter = new MappedDiagnosticContextRequestFieldSetter(requestPayLoadToStringConverter);

    @InjectMocks
    private InternalServerErrorResponseCreator internalServerErrorResponseCreator;

    @Test
    void create_givenGoodParameters_expectedSuccess() {
        final String userMessage = "user message";
        doReturn(userMessage).when(messagePropertyReader).getErrorMessage(any(), any());

        HttpServletRequest mockedRequest = new MockHttpServletRequest();
        ResponseEntity<ErrorResponse> errorResponseEntity = internalServerErrorResponseCreator.create(new ApplicationClientException("There was an error from the external provider in com.axp.c360.clientsbase.handler.EpccRestResponseErrorHandler", ErrorCode.GENERIC_4XX_ERROR), mockedRequest);
        Assertions.assertAll("Error response entity for internal server error",
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponseEntity.getStatusCode(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponseEntity.getStatusCode().getReasonPhrase(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(ErrorCode.GENERIC_5XX_ERROR, errorResponseEntity.getBody().getCode(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(ControllerExceptionHandler.GENERIC_5XX_HEADER_MESSAGE, errorResponseEntity.getBody().getMessage(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(userMessage, errorResponseEntity.getBody().getMoreInfo(), CommonAssertionMessages.VALUE_NOT_EQUAL));
    }
}
