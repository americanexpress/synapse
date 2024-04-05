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

import io.americanexpress.service.sample.imperativebook.model.DeleteBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.DeleteBookServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.stereotype.Component;

/**
 * DeleteBookService class is responsible for deleting the book service.
 *
 * @author Francois Gutt
 */
@Component
public class DeleteBookService extends BaseService<
            DeleteBookServiceRequest,
            DeleteBookServiceResponse
        > {

    /**
     * This method is responsible for executing the delete service.
     *
     * @param serviceRequest the service request
     */
    @Override
    protected DeleteBookServiceResponse doExecute(DeleteBookServiceRequest serviceRequest) {
        return new DeleteBookServiceResponse();
    }
}
