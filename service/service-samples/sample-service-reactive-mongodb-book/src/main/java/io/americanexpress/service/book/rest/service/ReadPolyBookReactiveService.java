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
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadFluxReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


/**
 * {@code ReadPolyBookReactiveService} retrieves books from the database given request.
 */
@Service
public class ReadPolyBookReactiveService extends BaseReadFluxReactiveService<ReadBookRequest, ReadBookResponse> {

    /**
     * Used to retrieve books from database.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new ReadPolyBookReactiveService.
     *
     * @param bookRepository the book repository
     */
    public ReadPolyBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves the list of books from database as requested in request.
     *
     * @param request the readBookRequest
     */
    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookRequest request) {
        return bookRepository.findAll()
                .map(ReadBookResponseCreator::create)
                .switchIfEmpty(Flux.empty());
    }
}
