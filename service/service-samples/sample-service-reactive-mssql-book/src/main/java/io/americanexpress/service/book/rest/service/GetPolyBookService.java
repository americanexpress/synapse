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
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookEntityMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.rest.service.BaseGetFluxReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code GetPolyBookService} Gets all book resources.
 */
@Service
public class GetPolyBookService extends BaseGetFluxReactiveService<ReadBookResponse> {

    /**
     * The bookRepository to access the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor taking in and autowiring BookRepository
     * @param bookRepository used to query the database.
     */
    public GetPolyBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * ExecuteRead will be used to get all book resources.
     * @param headers http server headers.
     * @return All book objects from the database.
     */
    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers) {
        return bookRepository.findAll()
                .map(BookEntityMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Not found.", ErrorCode.NOT_FOUND)));
    }
}
