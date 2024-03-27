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
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
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
     * bookRepository
     */
    private final BookRepository bookRepository;

    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * executeCreate will be used to create a book resource by request
     *
     * @param request
     * @return
     */
    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        return bookRepository.findByTitle(request.getTitle())
                .flatMap(entity -> entity != null ?
                        Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)) :
                        Mono.just(new BookEntity()))
                .switchIfEmpty(bookRepository.save(BookServiceMapper.populateBookEntityForCreation(request)))
                .map(BookServiceMapper::populateCreateBookResponse);
    }

}
