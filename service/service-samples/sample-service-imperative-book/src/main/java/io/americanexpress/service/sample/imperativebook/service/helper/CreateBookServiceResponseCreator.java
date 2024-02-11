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
package io.americanexpress.service.sample.imperativebook.service.helper;

import io.americanexpress.service.sample.imperativebook.model.BaseBook;
import io.americanexpress.service.sample.imperativebook.model.CreateBookServiceResponse;
import org.springframework.stereotype.Component;

/**
 * BaseBookServiceResponseCreator class is responsible for creating the book service response.
 *
 * @author Francois Gutt
 */
@Component
public class CreateBookServiceResponseCreator {

    /**
     * Create the book service response.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    public CreateBookServiceResponse create(BaseBook request) {

        var createBookServiceResponse = new CreateBookServiceResponse();
        createBookServiceResponse.setTitle(request.getTitle());
        createBookServiceResponse.setAuthor(request.getAuthor());
        createBookServiceResponse.setCost(request.getCost());
        createBookServiceResponse.setPublisher(request.getPublisher());
        createBookServiceResponse.setYear(request.getYear());
        
        return createBookServiceResponse;
    }
}
