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

import io.americanexpress.service.sample.imperativebook.model.ReadBookServiceRequest;
import io.americanexpress.service.sample.imperativebook.model.ReadBookServiceResponse;
import io.americanexpress.service.sample.imperativebook.service.helper.ReadBookServiceResponseCreator;
import io.americanexpress.synapse.service.imperative.service.BaseReadMonoImperativeService;
import org.springframework.stereotype.Component;

/**
 * {@code ReadBookService} class is responsible for reading the book service.
 *
 * @author Francois Gutt
 */
@Component
public class ReadBookService extends BaseReadMonoImperativeService<
            ReadBookServiceRequest,
            ReadBookServiceResponse
        > {

    /**
     * Constructor creates a new instance of ReadBookService with the given values.
     */
    private final ReadBookServiceResponseCreator readBookServiceResponseCreator;

    /**
     * Constructor creates a new instance of ReadBookService with the given values.
     *
     * @param readBookServiceResponseCreator the base book service response creator
     */
    public ReadBookService(ReadBookServiceResponseCreator readBookServiceResponseCreator) {
        this.readBookServiceResponseCreator = readBookServiceResponseCreator;
    }

    /**
     * Prototype for reading a resource.
     *
     * @param request the request
     * @return a read mono response
     */
    @Override
    protected ReadBookServiceResponse executeRead(ReadBookServiceRequest request) {
        return readBookServiceResponseCreator.create(request);
    }
}
