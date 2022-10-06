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
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUpdateBookService extends BaseUpdateReactiveService<UpdateBookRequest> {

    private final BookRepository bookRepository;

    public ReactiveUpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<Void> executeUpdate(UpdateBookRequest request) {
        return bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor())
                .map(book -> updateBook(book, request.getNumberOfCopies()))
                .flatMap(book -> {
                    if(book == null) {
                        return Mono.error(new NotFoundException("Book was not found"));
                    }else {
                        return bookRepository.save(book);
                    }
                }).then();
    }

    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }
}
