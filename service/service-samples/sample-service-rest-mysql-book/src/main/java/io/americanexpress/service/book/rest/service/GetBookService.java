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
import io.americanexpress.data.mysql.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code GetBookService} Gets specific book resource.
 */
@Service
public class GetBookService extends BaseGetMonoService<ReadBookResponse> {

    /**
     * bookRepository
     */
    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * executeRead will be used to retrieve a specific book resource by title
     * @param title
     * @return
     */
    @Override
    protected ReadBookResponse executeRead(HttpHeaders headers, String title) {
        BookEntity bookEntity = bookRepository.findByTitle(title);

        if (bookEntity == null) {
            throw new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null);
        }

        return BookServiceMapper.populateReadBookResponse(bookEntity);
    }
}
