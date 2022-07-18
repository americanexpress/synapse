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
package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class CreateResponseEntityCreator<O extends BaseServiceResponse> {

    /**
     * Create the POST response entity by specifying the creation location in the HTTP headers.
     *
     * @param serviceResponse body to set in the response entity
     * @return the POST response entity
     */
    public ResponseEntity<O> create(O serviceResponse) {

        // Default URI location in case the response identifier is null
        String responseIdentifier = "0";

        if (serviceResponse != null) {
            String identifier = serviceResponse.getId();
            if (StringUtils.isNotBlank(identifier)) {
                responseIdentifier = identifier.trim();
            }
        }

        // Build the resource location to specify in the response
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{identifier}")
                .buildAndExpand(responseIdentifier)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
