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
package com.americanexpress.synapse.service.rest.controller.exceptionhandler;

import com.americanexpress.synapse.framework.exception.ApplicationClientException;
import com.americanexpress.synapse.framework.exception.ApplicationServerException;
import com.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import com.americanexpress.synapse.framework.exception.model.ErrorCode;
import com.americanexpress.synapse.service.rest.model.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    private static final ErrorMessagePropertyReader ERROR_MESSAGE_PROPERTY_READER = new ErrorMessagePropertyReader(getErrorMessageSource());

    private static final InputValidationErrorHandler INPUT_VALIDATION_ERROR_HANDLER = new InputValidationErrorHandler();

    private static final ControllerExceptionHandler CONTROLLER_EXCEPTION_HANDLER = new ControllerExceptionHandler(ERROR_MESSAGE_PROPERTY_READER, INPUT_VALIDATION_ERROR_HANDLER);

    private static final String FIELD_DEFAULT_MESSAGE = "cannot be empty.";

    @Mock
    private BindingResult bindingResult;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private HttpInputMessage httpInputMessage;

    private static final MessageSource getErrorMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:error-messages");
        return messageSource;
    }

    @Test
    void handleApplicationException_whenApplicationExceptionContainsNoCause_expected400() {
        HttpServletRequest mockedRequest = new MockHttpServletRequest();
        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleApplicationClientException(new ApplicationClientException("There was an error from the external provider", ErrorCode.GENERIC_4XX_ERROR));
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_4XX_ERROR, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, "Your client has issued a malformed or illegal request.", HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleApplicationException_whenApplicationExceptionContainsCause_expected500() {
        HttpServletRequest mockedRequest = new MockHttpServletRequest();
        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleApplicationServerException(new ApplicationServerException(new IOException()), mockedRequest);
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_5XX_ERROR, ControllerExceptionHandler.GENERIC_5XX_HEADER_MESSAGE, "There was an error. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void handleMethodArgumentNotValidException_whenMethodArgumentNotValidExceptionContainsFieldErrors_expected400() {

        // Inject the mock binding result
        List<FieldError> fieldErrors = new ArrayList<>();
        FieldError fieldError = new FieldError("name", "Valid", FIELD_DEFAULT_MESSAGE);
        fieldErrors.add(fieldError);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        methodParameter.setTypeIndexForCurrentLevel(1);

        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleMethodArgumentNotValidException(new MethodArgumentNotValidException(methodParameter, bindingResult));
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_4XX_ERROR, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, FIELD_DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleHttpMessageNotReadableException_whenHttpMessageNotReadableExceptionContainsFieldErrors_expected400() {
        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleHttpMessageNotReadableException(new HttpMessageNotReadableException(FIELD_DEFAULT_MESSAGE, httpInputMessage));
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_4XX_ERROR, ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, FIELD_DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleThrowable_whenExceptionThrown_expected500() {
        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleThrowable(new NullPointerException());
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_5XX_ERROR, ControllerExceptionHandler.GENERIC_5XX_HEADER_MESSAGE, "There was an error. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void handleThrowable_whenErrorThrown_expected500() {
        ResponseEntity<ErrorResponse> errorResponseEntity = CONTROLLER_EXCEPTION_HANDLER.handleThrowable(new Error());
        assertErrorResponse(errorResponseEntity, ErrorCode.GENERIC_5XX_ERROR, ControllerExceptionHandler.GENERIC_5XX_HEADER_MESSAGE, "There was an error. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void assertErrorResponse(ResponseEntity<ErrorResponse> errorResponseEntity, ErrorCode expectedErrorCode, String expectedHeaderMessage, String expectedUserMessage, HttpStatus expectedStatusCode) {
        ErrorResponse actual = errorResponseEntity.getBody();
        assertAll("Error response values",
                () -> assertEquals(expectedErrorCode, actual.getCode()),
                () -> assertEquals(expectedHeaderMessage, actual.getMessage()),
                () -> assertEquals(expectedUserMessage, actual.getMoreInfo()),
                () -> assertEquals(expectedStatusCode, errorResponseEntity.getStatusCode()));
    }
}
