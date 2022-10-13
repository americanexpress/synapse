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
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code ReadBookReactiveService} is the service class for retrieving a book from the Cassandra Book database.
 */
@Service
public class ReadBookReactiveService extends BaseGetMonoReactiveService<ReadBookResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new ReadBookReactiveService.
     *
     * @param bookRepository the book repository
     */
    public ReadBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<ReadBookResponse> executeRead(String title) {
        return bookRepository.findByTitle(title)
                .map(ReadBookResponseCreator::create)
                .switchIfEmpty(Mono.empty());
    }
}
