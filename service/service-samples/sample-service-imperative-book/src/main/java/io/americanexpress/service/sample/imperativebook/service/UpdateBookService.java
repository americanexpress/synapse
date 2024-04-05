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
package io.americanexpress.service.sample.imperativebook.service;

import io.americanexpress.service.sample.imperativebook.model.UpdateBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.UpdateBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.helper.UpdateBookServiceResponseCreator;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.stereotype.Component;

/**
 * UpdateBookService class is responsible for updating the book service.
 *
 * @author Francois Gutt
 */
@Component
public class UpdateBookService extends BaseService<
            UpdateBookServiceRequest,
            UpdateBookServiceResponse
        > {

    /**
     * Constructor creates a new instance of UpdateBookService with the given values.
     */
    private final UpdateBookServiceResponseCreator updateBookServiceResponseCreator;

    /**
     * Constructor creates a new instance of UpdateBookService with the given values.
     *
     * @param updateBookServiceResponseCreator the base book service response creator
     */
    public UpdateBookService(UpdateBookServiceResponseCreator updateBookServiceResponseCreator) {
        this.updateBookServiceResponseCreator = updateBookServiceResponseCreator;
    }

    /**
     * Prototype for updating a resource.
     *
     * @param request body received from the controller
     */
    @Override
    protected UpdateBookServiceResponse doExecute(UpdateBookServiceRequest request) {
        var response = updateBookServiceResponseCreator.create(request);
        return response;
    }
}
