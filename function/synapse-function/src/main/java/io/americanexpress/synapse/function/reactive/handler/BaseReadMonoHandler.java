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

import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public abstract class BaseReadMonoHandler<T> extends BaseHandler {

    private final Class<T> validationClass;

    private final Validator validator;

    protected BaseReadMonoHandler(Class<T> clazz, Validator validator) {
        this.validationClass = clazz;
        this.validator = validator;
    }

    /**
     * Get a single resource
     *
     * @param request body received from the controller
     * @return a single resource from the back end service.
     */
    public Mono<ServerResponse> read(ServerRequest request) {
        logger.entry(request);

        return request.bodyToMono(this.validationClass)
                .flatMap(body -> {
                    Errors errors = new BeanPropertyBindingResult(body, this.validationClass.getName());
                    this.validator.validate(body, errors);

                    if (errors.getAllErrors().isEmpty()) {
                        return executeRead(body);
                    } else {
                        return onValidationErrors(errors, body, request);
                    }
                });

    }

    protected abstract Mono<ServerResponse> executeRead(T request);

    protected Mono<ServerResponse> onValidationErrors(Errors errors, T invalidBody, final ServerRequest request) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getAllErrors().get(0).getDefaultMessage());
    }
}
