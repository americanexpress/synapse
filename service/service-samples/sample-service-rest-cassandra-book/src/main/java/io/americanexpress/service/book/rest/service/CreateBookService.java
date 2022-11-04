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
import io.americanexpress.service.book.rest.service.helper.BookEntityCreator;
import io.americanexpress.synapse.framework.exception.ApplicationServerException;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code CreateBookService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class CreateBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new CreateBookService.
     *
     * @param bookRepository the book repository
     */
    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected CreateBookResponse executeCreate(HttpHeaders headers, CreateBookRequest request) {
        final ClassNotFoundException classNotFoundException = new ClassNotFoundException();
        throw new ApplicationServerException(classNotFoundException);
//        BookEntity book = BookEntityCreator.create(request.getTitle(), request.getAuthor(), 1);
//        bookRepository.save(book);
//        return new CreateBookResponse();
    }
}
