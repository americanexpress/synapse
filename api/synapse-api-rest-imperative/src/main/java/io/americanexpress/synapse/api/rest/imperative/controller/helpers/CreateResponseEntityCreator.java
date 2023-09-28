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
package io.americanexpress.synapse.api.rest.imperative.controller.helpers;

import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

/**
 * {@code CreateResponseEntityCreator} Helps creates ResponseEntity.
 * @param <O> BaseServiceResponse will be used for the response object.
 * @author Francois Gutt
 */
public class CreateResponseEntityCreator<O extends BaseServiceResponse> {

    /**
     * Create the POST response entity by specifying the creation location in the HTTP headers.
     * @param serviceResponse body to set in the response entity.
     * @return the POST response entity.
     */
    public static <O extends BaseServiceResponse> ResponseEntity<O> create(O serviceResponse) {

        // Default URI location in case the response identifier is null
        String responseId = "0";

        if (serviceResponse != null) {
            String id = serviceResponse.getId();
            if (StringUtils.isNotBlank(id)) {
                responseId = id.trim();
            }
        }

        // Build the resource location to specify in the response
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{identifier}")
                .buildAndExpand(responseId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
