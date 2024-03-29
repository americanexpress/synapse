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

import io.americanexpress.data.mysql.book.dao.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code ReadBookService} Gets a book resource by request criteria.
 */
@Service
public class ReadBookService extends BaseReadMonoReactiveService<ReadBookRequest, ReadBookResponse> {

    /**
     * The bookRepository to access the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor taking in and autowiring BookRepository
     *
     * @param bookRepository Will be used to query the database.
     */
    public ReadBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * ExecuteRead will be used to retrieve a book resource by request.
     *
     * @param headers Http server headers.
     * @param request Request object used to query the database with title and author.
     * @return Response object of the title and author being queried.
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookRequest request) {
        return bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor())
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Not found.", ErrorCode.NOT_FOUND, (String[]) null)));
    }
}
