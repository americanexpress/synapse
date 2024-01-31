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
import io.americanexpress.service.book.rest.model.ReadBookServiceRequest;
import io.americanexpress.service.book.rest.model.ReadBookServiceResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code ReadBookService} reads a book from the database using id, author, or title.
 */
@Service
public class ReadBookService extends BaseReadMonoService<ReadBookServiceRequest, ReadBookServiceResponse> {

    /**
     * The book repository for querying the database.
     */
    private final BookRepository bookRepository;

    /**
     * Maps various request / responses into entities and vice versa.
     */
    private final BookServiceMapper bookServiceMapper;

    /**
     * Creates a new instance of {@code ReadBookService} with the specified values.
     * @param bookRepository the repository for the database.
     * @param bookServiceMapper maps various request / responses into entities and vice versa.
     */
    public ReadBookService(BookRepository bookRepository, BookServiceMapper bookServiceMapper) {
        this.bookRepository = bookRepository;
        this.bookServiceMapper = bookServiceMapper;
    }

    /**
     * Reads a book's information from the database.
     *
     * @param headers the http header map.
     * @param request the request.
     * @return the {@link ReadBookServiceResponse}.
     */
    @Override
    protected ReadBookServiceResponse executeRead(HttpHeaders headers, ReadBookServiceRequest request) {
        BookEntity bookEntity = null;

        if (request.getId() != null) {
            bookEntity = bookRepository.findById(request.getId()).orElse(null);
        } else if (StringUtils.isNotBlank(request.getAuthor())) {
            bookEntity = bookRepository.findByAuthor(request.getAuthor());
        } else if (StringUtils.isNotBlank(request.getTitle())) {
            bookEntity = bookRepository.findByTitle(request.getTitle());
        }

        return bookServiceMapper.mapEntityToReadResponse(bookEntity);
    }
}
