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

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReadPolyReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * {@link ReadPolyBookReactiveService} is the service class for retrieving books from the Cassandra Book database.
 */
@Service
public class ReadPolyBookReactiveService extends BaseReadPolyReactiveService<ReadPolyBookRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new Read poly book reactive service.
     *
     * @param bookRepository the book repository
     */
    public ReadPolyBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers, ReadPolyBookRequest request) {
        Flux<ReadBookResponse> readBookResponseFlux;
        if(request.getPageInformation() != null) {
            readBookResponseFlux = bookRepository.findAll()
                    .map(ReadBookResponseCreator::create)
                    .switchIfEmpty(Flux.empty())
                    .skip((long) request.getPageInformation().getPage() * request.getPageInformation().getSize()).take(request.getPageInformation().getSize());
        }else {
            readBookResponseFlux = bookRepository.findAll()
                    .map(ReadBookResponseCreator::create)
                    .switchIfEmpty(Flux.empty());
        }
       return readBookResponseFlux;
    }
}
