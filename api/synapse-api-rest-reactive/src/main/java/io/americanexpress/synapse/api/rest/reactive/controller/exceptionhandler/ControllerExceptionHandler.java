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
package io.americanexpress.synapse.api.rest.reactive.controller.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.api.rest.reactive.model.ErrorResponse;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * {@code ControllerExceptionHandler} handles all the exceptions and errors thrown by the application.
 * The order is -2 to capture exceptions from other filters.
 * @author Wendy Hu
 */
@Component
@Order(-2)
@RequiredArgsConstructor
public class ControllerExceptionHandler implements WebExceptionHandler {

    /**
     * Logs exceptions.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Handles input validation errors.
     */
    private final InputValidationErrorHandler inputValidationErrorHandler;

    /**
     * Handles application exceptions.
     * @param exchange the server web exchange
     * @param throwable the throwable exception
     * @return a mono void
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        ErrorResponse errorResponse = null;

        if (throwable instanceof WebExchangeBindException webExchangeBindException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            errorResponse = inputValidationErrorHandler.handleInputValidationErrorMessage(webExchangeBindException);
        } else if (throwable instanceof ServerWebInputException serverWebInputException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            errorResponse = new ErrorResponse(ErrorCode.GENERIC_4XX_ERROR, serverWebInputException.getMessage(), throwable.getMessage(), serverWebInputException.getLocalizedMessage());
        } else if (throwable instanceof ApplicationClientException applicationClientException) {
            exchange.getResponse().setRawStatusCode(applicationClientException.getErrorCode().getHttpStatus().value());
            errorResponse = new ErrorResponse(applicationClientException.getErrorCode(), applicationClientException.getErrorCode().getMessage(),
                    throwable.getMessage(), applicationClientException.getDeveloperMessage());
        } else if (throwable instanceof ApplicationServerException) {
            errorResponse = handleInternalServerError(throwable);
        } else {
            errorResponse = handleInternalServerError(throwable);
        }

        return createResponseBody(exchange, errorResponse);
    }

    /**
     * Creates the response body with the error response.
     * @param exchange the server web exchange
     * @param errorResponse the error response
     * @return a mono void
     */
    public Mono<Void> createResponseBody(ServerWebExchange exchange, ErrorResponse errorResponse) {
        DataBuffer buffer;
        try {
            buffer = exchange.getResponse().bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(errorResponse));
        } catch (JsonProcessingException exception) {
            throw new ApplicationServerException(exception);
        }

        exchange.getResponse().setRawStatusCode(errorResponse.getCode().getHttpStatus().value());
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    /**
     * Handles internal server errors.
     * @param throwable the throwable exception
     * @return the error response
     */
    public ErrorResponse handleInternalServerError(Throwable throwable) {
        logger.warn("Error", throwable);
        return new ErrorResponse(ErrorCode.GENERIC_5XX_ERROR, ErrorCode.GENERIC_5XX_ERROR.getMessage(), throwable.getMessage(),
                ApplicationServerException.getStackTrace(throwable, System.lineSeparator()));
    }
}
