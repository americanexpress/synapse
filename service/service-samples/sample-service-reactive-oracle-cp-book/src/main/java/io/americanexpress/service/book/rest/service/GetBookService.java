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

import io.americanexpress.data.oracle.cp.book.service.BookPersistenceService;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code GetBookService} Gets specific book resource.
 */
@Service
public class GetBookService extends BaseGetMonoReactiveService<ReadBookResponse> {

    /**
     * bookPersistenceService
     */
    private final BookPersistenceService bookPersistenceService;

    /**
     * Constructor of {@Ccode GetBookService} with provided {@link BookPersistenceService}.
     * @param bookPersistenceService The {@link BookPersistenceService} used to perform CRUD operations asynchronously
     *                               through a connection pool.
     */
    public GetBookService(BookPersistenceService bookPersistenceService) {
        this.bookPersistenceService = bookPersistenceService;
    }

    /**
     * executeRead will be used to retrieve a specific book resource by title
     * @param headers The {@link HttpHeaders} of the request.
     * @param title The title of the book.
     * @return A {@link Mono} emitting a {@link ReadBookResponse}.
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, String title) {
        return bookPersistenceService.executeFindByTitle(title)
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)));
    }
}
