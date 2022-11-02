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

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetBookService extends BaseGetMonoReactiveService<ReadBookResponse> {

    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    protected Mono<ReadBookResponse> executeRead(String identifier) {
        return bookRepository.findByTitle(identifier)
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.empty());
    }
}
