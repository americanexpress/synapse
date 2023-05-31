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
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code ReadMonoBookReactiveService} service layer for retrieving a book resources
 */
@Service
public class ReadBookReactiveService extends BaseReadMonoReactiveService<ReadBookRequest, ReadBookResponse> {

    /**
     * Used to retrieve book from redis store.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new Read book reactive service.
     *
     * @param bookRepository the book repository
     */
    public ReadBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves book from redis store using book title provided in request.
     *
     * @param headers the httpHeaders
     * @param request the request
     * @return a mono read book response if book is found in redis store
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookRequest request) {
       return bookRepository.findByTitle(request.getTitle())
               .map(book -> {
                   ReadBookResponse readBookResponse = new ReadBookResponse();
                   readBookResponse.setTitle(book.getTitle());
                   readBookResponse.setAuthor(book.getAuthor());
                   return readBookResponse;
               });
    }

}
