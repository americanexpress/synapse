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

import io.americanexpress.data.book.dao.BookRepository;
import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@code ReactiveCreateMonoBookService} creates book in the database given request.
 */
@Service
public class ReactiveCreateMonoBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    @Autowired
    public ReactiveCreateMonoBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected CreateBookResponse executeCreate(CreateBookRequest request) {
        CreateBookResponse createBookResponse = null;

        BookEntity book = new BookEntity();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());

        if(bookRepository.save(book).block() != null) {
            createBookResponse =  new CreateBookResponse();
        }

        return createBookResponse;
    }
}
