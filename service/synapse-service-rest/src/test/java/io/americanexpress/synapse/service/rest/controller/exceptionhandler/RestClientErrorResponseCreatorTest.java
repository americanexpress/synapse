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

import io.americanexpress.synapse.framework.exception.helper.ErrorMessagePropertyReader;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.framework.test.CommonAssertionMessages;
import io.americanexpress.synapse.service.rest.model.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * {@code RestClientErrorResponseCreator} tests the {@link RestClientErrorResponseCreator}
 */
@ExtendWith(MockitoExtension.class)
class RestClientErrorResponseCreatorTest {

    @Mock
    private ErrorMessagePropertyReader messagePropertyReader;

    @InjectMocks
    private RestClientErrorResponseCreator restClientErrorResponseCreator;

    @Test
    void create_givenClientInternalServerError_expected400BadRequestErrorResponse() {
        doReturn(StringUtils.EMPTY).when(messagePropertyReader).getErrorMessage(any());

        final String responseBody = "{ \"response\": \"body\" }";
        HttpServerErrorException exception = HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpHeaders.EMPTY, responseBody.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        ResponseEntity<ErrorResponse> errorResponseEntity = restClientErrorResponseCreator.create(exception);
        String developerMessage = Objects.requireNonNull(errorResponseEntity.getBody()).getDeveloperMessage();
        Assertions.assertAll("Error response entity for 500 client response",
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseEntity.getStatusCode().value(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), ((HttpStatus)errorResponseEntity.getStatusCode()).getReasonPhrase(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(ErrorCode.GENERIC_4XX_ERROR, errorResponseEntity.getBody().getCode(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(ControllerExceptionHandler.GENERIC_4XX_HEADER_MESSAGE, errorResponseEntity.getBody().getMessage(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertEquals(StringUtils.EMPTY, errorResponseEntity.getBody().getMoreInfo(), CommonAssertionMessages.VALUE_NOT_EQUAL),
                () -> assertTrue(developerMessage.contains(responseBody), CommonAssertionMessages.VALUE_NOT_EQUAL));
    }
}
