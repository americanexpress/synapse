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
package io.americanexpress.synapse.service.reactive.rest.controller.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.service.reactive.rest.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code ControllerExceptionHandler} handles all the exceptions and errors thrown by the application.
 *
 * @author Wendy Hu
 */
@Component
@Order(-2)
@RequiredArgsConstructor
public class ControllerExceptionHandler implements WebExceptionHandler {

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

        if (throwable instanceof WebExchangeBindException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            errorResponse = inputValidationErrorHandler.handleInputValidationErrorMessage((WebExchangeBindException) throwable);
        } else if (throwable instanceof ApplicationClientException) {
            exchange.getResponse().setStatusCode(((ApplicationClientException) throwable).getErrorCode().getHttpStatus());
            errorResponse = new ErrorResponse(((ApplicationClientException) throwable).getErrorCode(), ((ApplicationClientException) throwable).getErrorCode().getMessage(),
                    throwable.getMessage(), ((ApplicationClientException) throwable).getDeveloperMessage());
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
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
