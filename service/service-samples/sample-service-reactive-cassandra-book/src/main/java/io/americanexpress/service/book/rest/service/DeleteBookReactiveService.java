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
import io.americanexpress.synapse.service.rest.service.reactive.BaseDeleteReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * {@code DeleteBookReactiveService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class DeleteBookReactiveService extends BaseDeleteReactiveService {

    /**
     * Used to delete book in database.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new DeleteBookReactiveService.
     *
     * @param bookRepository the book repository
     */
    public DeleteBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Checks if book exist in database, if it does delete book.
     * @param title the title of book to delete
     */
    @Override
    protected Mono<Void> executeDelete(String title) {
        return bookRepository.findByTitle(title)
                .map(bookRepository::delete)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book Not Found")))
                .then();
    }
}
