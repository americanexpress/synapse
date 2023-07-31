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
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.reactive.BaseDeleteReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * {@code DeleteBookService} Deletes a book resource.
 */
@Service
public class DeleteBookService extends BaseDeleteReactiveService {

    /**
     * bookRepository
     */
    private final BookPersistenceService bookPersistenceService;

    public DeleteBookService(BookPersistenceService bookPersistenceService) {
        this.bookPersistenceService = bookPersistenceService;
    }

    /**
     * executeDelete will be used to delete book resource by title
     * @param title
     */
    @Override
    protected Mono<Void> executeDelete(HttpHeaders headers, String title) {
        return bookPersistenceService.executeFindByTitle(title)
                .publishOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)))
                .doOnSuccess(bookEntity -> bookPersistenceService.executeDeleteByTitle(bookEntity.getTitle()).subscribe())
                .then();
    }
}
