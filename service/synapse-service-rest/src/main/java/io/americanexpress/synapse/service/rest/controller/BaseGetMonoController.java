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
package io.americanexpress.synapse.service.rest.controller;

import io.americanexpress.synapse.service.rest.controller.helpers.MonoResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * <code>BaseGetMonoController</code> is base class for read mono controller. This controller handles POST method requests, but specifically for read purposes.
 * This controller returns a single object.
 *
 * @param <O> the type parameter
 * @param <S> the type parameter
 */
public class BaseGetMonoController<O extends BaseServiceResponse, S extends BaseGetMonoService<O>> extends BaseController<S> {

    /**
     * Read response entity.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Read operation based on path.", description = "Read one resource based on a path variable.")
    @GetMapping("/{id}")
    public ResponseEntity<O> read(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        logger.entry(id);

        final O response = service.read(headers, id);
        ResponseEntity<O> responseEntity = MonoResponseEntityCreator.create(response);

        logger.exit(responseEntity);
        return responseEntity;
    }
}
