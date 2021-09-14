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
package io.americanexpress.service.book.service;

import io.americanexpress.service.book.model.ReadBookRequest;
import io.americanexpress.service.book.model.ReadBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadPolyService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ReadBookService extends BaseReadPolyService<ReadBookRequest, ReadBookResponse> {


    @Override
    protected Page<ReadBookResponse> executeRead(ReadBookRequest request) {

        ReadBookResponse readBookResponse = new ReadBookResponse();
        readBookResponse.setAuthor(request.getAuthor());
        readBookResponse.setTitle(request.getTitle());

        return (Page<ReadBookResponse>) readBookResponse;
    }
}
