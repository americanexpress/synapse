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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The type Base read mono controller.
 *
 * @param <O> the type parameter
 * @param <S> the type parameter
 */
public class BaseGetMonoController<O extends BaseServiceResponse, S extends BaseGetMonoService<O>> extends BaseController<S> {

    @Autowired
    private MonoResponseEntityCreator<O> monoResponseEntityCreator;

    /**
     * Read response entity.
     *
     * @param identifier the identifier
     * @return the response entity
     */
    @GetMapping("/{identifier}")
    @Operation(summary = "Read Operation from Path", description = "Reads a resource based on path parameter.")
    public ResponseEntity<O> read(@PathVariable String identifier) {
        logger.entry(identifier);
        final O response = service.read(identifier);
        ResponseEntity<O> responseEntity = monoResponseEntityCreator.create(response);
        logger.exit(responseEntity);
        return responseEntity;
    }

}
