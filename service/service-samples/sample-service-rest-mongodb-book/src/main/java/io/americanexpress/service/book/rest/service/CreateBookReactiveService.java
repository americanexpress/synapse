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
import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.rest.service.reactive.BaseCreateReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code CreateBookReactiveService} creates book in the database given request.
 */
@Service
public class CreateBookReactiveService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    public CreateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<CreateBookResponse> executeCreate(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());

        return bookRepository.save(bookEntity).map(book -> new CreateBookResponse()).switchIfEmpty(Mono.empty());

    }
}
