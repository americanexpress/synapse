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

import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code BaseDeleteController} class specifies the prototypes for listening for requests from the consumer
 * to Delete (DELETE) a resource. This controller expects only one entry per request.
 *
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public abstract class BaseDeleteController<S extends BaseDeleteService> extends BaseController<S> {

    /**
     * Delete a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param identifier of the resource to be deleted
     */
    @DeleteMapping("/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Delete Operation", summary = "Deletes a resource")
    public void delete(@RequestHeader HttpHeaders headers,  @PathVariable String identifier) {
        logger.entry(identifier);

        service.delete(headers, identifier);

        logger.exit();
    }
}
