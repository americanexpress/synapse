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
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code CreateBookService} creates a new book and inserts it into the database.
 */
@Service
public class CreateBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    /**
     * The book repository for querying the database.
     */
    private final BookRepository bookRepository;

    /**
     * Maps various request / responses into entities and vice versa.
     */
    private final BookServiceMapper bookServiceMapper;

    /**
     * Creates a new instance of {@code CreateBookService} with the specified values.
     * @param bookRepository the repository for the database.
     * @param bookServiceMapper maps various request / responses into entities and vice versa.
     */
    public CreateBookService(BookRepository bookRepository, BookServiceMapper bookServiceMapper) {
        this.bookRepository = bookRepository;
        this.bookServiceMapper = bookServiceMapper;
    }

    /**
     * Saves the book into the database.
     * @param headers the http header map.
     * @param request the request.
     * @return the {@link CreateBookResponse}.
     */
    @Override
    protected CreateBookResponse executeCreate(HttpHeaders headers, CreateBookRequest request) {
        var bookEntity = bookServiceMapper.mapCreateRequestToEntity(request);
        bookRepository.save(bookEntity);
        return bookServiceMapper.mapCreateBookResponse(bookEntity);
    }
}
