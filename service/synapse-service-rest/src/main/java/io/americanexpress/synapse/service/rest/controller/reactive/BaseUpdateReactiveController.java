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
package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.service.reactive.BaseUpdateReactiveService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * {@code BaseUpdateReactiveController} class specifies the prototypes for listening for requests from the consumer
 * to Update (PUT/PATCH) a resource. This controller expects only one object.
 * @param <I>
 * @param <S>
 */
public class BaseUpdateReactiveController<I extends BaseServiceRequest, S extends BaseUpdateReactiveService<I>> extends BaseController<S> {

    /**
     * Update a single resource entirely.
     *
     * @param serviceRequest body from the consumer
     */
    @Operation(tags = "Update Operation", summary = "Updates a resource reactively")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> update(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        var results = service.update(headers, serviceRequest);
        var responseEntity = results
                .map(ResponseEntity::ok);

        logger.exit();
        return responseEntity;
    }
}
