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
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.synapse.service.rest.service.reactive.BaseUpdateReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * {@code UpdateBookReactiveService} updates book in the database given request.
 */
@Service
public class UpdateBookReactiveService extends BaseUpdateReactiveService<UpdateBookRequest> {

    private final BookRepository bookRepository;

    public UpdateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<Void> executeUpdate(UpdateBookRequest request) {
        return bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book Not Found")))
                .map(book -> updateBook(book, request.getNumberOfCopies()))
                .flatMap(bookRepository::save).then();
    }

    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }
}
