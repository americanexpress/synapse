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

import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.helper.CreateBookServiceResponseCreator;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.stereotype.Component;

/**
 * CreateBookService class is responsible for creating the book service.
 *
 * @author Francois Gutt
 */
@Component
public class CreateBookService extends BaseService<
            CreateBookServiceRequest,
            CreateBookServiceResponse
        > {

    /**
     * CreateBookServiceResponseCreator to create the response.
     */
    private final CreateBookServiceResponseCreator createBookServiceResponseCreator;

    /**
     * CreateBookService constructor.
     *
     * @param createBookServiceResponseCreator to create the response
     */
    public CreateBookService(CreateBookServiceResponseCreator createBookServiceResponseCreator) {
        this.createBookServiceResponseCreator = createBookServiceResponseCreator;
    }

    /**
     * Prototype for adding a resource.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    @Override
    protected CreateBookServiceResponse doExecute(CreateBookServiceRequest request) {
        return createBookServiceResponseCreator.create(request);
    }
}
