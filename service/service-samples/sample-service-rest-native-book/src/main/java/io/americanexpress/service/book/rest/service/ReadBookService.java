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
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


/**
 * {@link ReadBookService} is the service for /v1/books/inquiry_results.
 */
@Service
public class ReadBookService extends BaseReadMonoService<ReadBookRequest, ReadBookResponse> {

    /**
     * Used to read from mongodb database.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new Read book service.
     *
     * @param bookRepository the book repository
     */
    public ReadBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Read book from mongodb database.
     * @param headers http headers
     * @param request the request to read book from database
     */
    // RegisterReflectionForBinding lets spring aot know in advance the request and response type for the service.
    @Override
    @RegisterReflectionForBinding({ReadBookRequest.class, ReadBookResponse.class})
    protected ReadBookResponse executeRead(HttpHeaders headers, ReadBookRequest request) {
        ReadBookResponse readBookResponse = new ReadBookResponse();

        BookEntity bookEntity = bookRepository.findByTitle(request.getTitle());
        if(bookEntity != null) {
            readBookResponse.setTitle(bookEntity.getTitle());
            readBookResponse.setAuthor(bookEntity.getAuthor());
        }
        return readBookResponse;
    }
}
