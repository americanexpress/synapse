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
import io.americanexpress.service.book.rest.service.helper.BookEntityMapper;
import io.americanexpress.synapse.service.reactive.rest.service.BaseCreateReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code CreateBookService} Creates a new book resource.
 */
@Service
public class CreateBookService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    /**
     * The bookRepository to access the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor taking in and autowiring BookRepository.
     * @param bookRepository used to query the database.
     */
    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * ExecuteCreate will be used to create a book resource by request
     * @param headers Http server headers.
     * @param request Object used for creating a bookEntity object.
     * @return Returns what was created.
     */
    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        return bookRepository.save(BookEntityMapper.populateBookEntityForCreation(request))
                .map(BookEntityMapper::populateCreateBookResponse);
    }

}
