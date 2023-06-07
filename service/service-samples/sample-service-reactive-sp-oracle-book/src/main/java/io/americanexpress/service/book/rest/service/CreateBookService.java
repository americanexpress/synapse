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

import io.americanexpress.data.oracle.book.dao.BookSPRepository;
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
    private final BookSPRepository bookSPRepository;

    public CreateBookService(BookSPRepository bookSPRepository) {
        this.bookSPRepository = bookSPRepository;
    }

    /**
     * executeCreate will be used to create a book resource by request
     *
     * @param request
     * @return
     */
    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        return bookSPRepository.getBookByTitle(request.getTitle())
                .flatMap(entity -> entity != null ?
                        Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null))
                        : insertBook(request)
                );
    }

    /**
     * Encapsulate insert book method that will return a Mono of CreateBookResponse.
     *
     * @param request Create book request.
     * @return A Mono of Create Book Response.
     */
    private Mono<CreateBookResponse> insertBook(CreateBookRequest request) {
        return bookSPRepository.insertBook(request.getTitle(),
                        request.getAuthor(),
                        request.getCreatedBy(),
                        "test")
                .flatMap(Void -> bookSPRepository.getBookByTitle(request.getTitle()))
                .map(BookServiceMapper::populateCreateBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("An error occurred.", ErrorCode.GENERIC_5XX_ERROR, (String[]) null)));
    }

}
