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

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseCreateReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * {@code CreateBookReactiveService} service layer to create and save book to redis store.
 */
@Service
public class CreateBookReactiveService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    /**
     * Used to save book to redis store.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new Create book reactive service.
     *
     * @param bookRepository the book repository
     */
    public CreateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Creates book from request and saves to redis store.
     *
     * @param headers the httpHeaders
     * @param request the request
     * @return mono CreateBookResponse if save is successful
     */
    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        BookEntity book = new BookEntity(request.getTitle(), request.getAuthor());
        book.setIdentifier(UUID.randomUUID().toString());
        return bookRepository.save(book).map(bookEntity -> new CreateBookResponse());
    }
}
