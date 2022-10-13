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
package io.americanexpress.synapse.function.reactive.handler;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;

public abstract class BaseHandler<T> {

    /**
     * Logger for the base service.
     */
    protected final XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected Validator validator;

    protected Class<T> validationClass;

    @Autowired
    protected void setValidator(Validator validator){
        this.validator = validator;
    }

    protected Mono<ServerResponse> onValidationErrors(Errors errors, T invalidBody, final ServerRequest request) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().get(0).getDefaultMessage());
    }

    protected void initialize() {

        // Find the first child class of BaseClient
        // in order to determine the request and response types
        Class<?> classType = getClass();
        while(classType.getSuperclass() != BaseHandler.class) {
            classType = classType.getSuperclass();
        }

        // Set the client request and response types
        // which were found from the generic type arguments of BaseClient<0 = I, 1 = O, 2 = H>
        // Here, the client request type is from the 0th generic type argument <I>
        // and the client response type is from the 1st generic type argument <O>
        ParameterizedType parameterizedType = ((ParameterizedType) getClass().getGenericSuperclass());
        validationClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

    }

}
