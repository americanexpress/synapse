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
import io.americanexpress.service.sample.imperativebook.model.UpdateBookServiceResponse;
import org.springframework.stereotype.Component;

/**
 * UpdateBookServiceResponseCreator class is responsible for creating the book service response.
 *
 * @author Francois Gutt
 */
@Component
public class UpdateBookServiceResponseCreator {

    /**
     * Create the book service response.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    public UpdateBookServiceResponse create(BaseBook request) {

        var updateBookServiceResponse = new UpdateBookServiceResponse();
        updateBookServiceResponse.setTitle(request.getTitle());
        updateBookServiceResponse.setAuthor(request.getAuthor());
        updateBookServiceResponse.setCost(request.getCost());
        updateBookServiceResponse.setPublisher(request.getPublisher());
        updateBookServiceResponse.setYear(request.getYear());
        
        return updateBookServiceResponse;
    }
}
