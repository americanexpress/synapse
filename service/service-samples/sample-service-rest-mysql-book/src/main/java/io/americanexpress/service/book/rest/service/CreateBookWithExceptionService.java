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
package io.americanexpress.service.book.rest.service;

import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.framework.exception.ApplicationNotFoundException;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * CreateBookService class is responsible for creating the book service.
 *
 * @author Francois Gutt
 */
@Component
public class CreateBookWithExceptionService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    /**
     * Prototype for adding a resource.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    @Override
    protected CreateBookResponse executeCreate(HttpHeaders headers, CreateBookRequest request) {
        throw new ApplicationNotFoundException("This is a test exception.");
    }
}
