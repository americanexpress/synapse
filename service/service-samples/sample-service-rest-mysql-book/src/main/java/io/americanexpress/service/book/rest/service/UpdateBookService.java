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
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code UpdateBookService} Updates a book resource by request criteria.
 */
@Service
public class UpdateBookService extends BaseUpdateService<UpdateBookRequest> {

    /**
     * bookRepository
     */
    private BookRepository bookRepository;

    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * executeUpdate will be used to update a book resource by request.
     * @param request
     */
    @Override
    protected void executeUpdate(HttpHeaders headers, UpdateBookRequest request) {
        BookEntity bookEntity = bookRepository.findByTitle(request.getTitle());
        if (bookEntity != null) {
            bookRepository.save(BookServiceMapper.populateBookEntityForUpdate(request, bookEntity));
        } else {
            throw new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null);
        }
    }
}
