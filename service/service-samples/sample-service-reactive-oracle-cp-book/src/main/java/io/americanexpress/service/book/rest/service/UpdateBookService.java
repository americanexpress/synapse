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
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.rest.service.BaseUpdateReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * {@code UpdateBookService} Updates a book resource by request criteria.
 */
@Service
public class UpdateBookService extends BaseUpdateReactiveService<UpdateBookRequest> {

    /**
     * bookPersistenceService
     */
    private BookPersistenceService bookPersistenceService;

    /**
     * Constructor of {@Ccode UpdateBookService} with provided {@link BookPersistenceService}.
     *
     * @param bookPersistenceService The {@link BookPersistenceService} used to perform CRUD operations asynchronously
     *                               through a connection pool.
     */
    public UpdateBookService(BookPersistenceService bookPersistenceService) {
        this.bookPersistenceService = bookPersistenceService;
    }

    /**
     * executeUpdate will be used to update a book resource by request.
     *
     * @param headers The {@link HttpHeaders} of the request.
     * @param request The {@link UpdateBookRequest} is used to update a book resource in the database.
     * @return A {@link Mono<Void>} that completes when the update operation is done.
     */
    @Override
    protected Mono<Void> executeUpdate(HttpHeaders headers, UpdateBookRequest request) {
        return bookPersistenceService.executeFindByTitleAndAuthor(request.getTitle(), request.getAuthor())
                .publishOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)))
                .doOnSuccess(bookEntity -> bookPersistenceService.executeSave(BookServiceMapper.populateBookEntityForUpdate(request, bookEntity)).subscribe())
                .then();
    }
}
