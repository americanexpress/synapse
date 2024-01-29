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

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code UpdateBookService} updates a book's information in the database.
 */
@Service
public class UpdateBookService extends BaseUpdateService<UpdateBookRequest> {

    /**
     * The book repository for querying the database.
     */
    private final BookRepository bookRepository;

    /**
     * Maps various request / responses into entities and vice versa.
     */
    private final BookServiceMapper bookServiceMapper;

    /**
     * Creates a new instance of {@code UpdateBookService} with the specified values.
     * @param bookRepository the repository for the database.
     * @param bookServiceMapper maps various request / responses into entities and vice versa.
     */
    public UpdateBookService(BookRepository bookRepository, BookServiceMapper bookServiceMapper) {
        this.bookRepository = bookRepository;
        this.bookServiceMapper = bookServiceMapper;
    }

    /**
     * Updates book information in the database.
     *
     * @param headers the headers.
     * @param request the {@link UpdateBookRequest}.
     */
    @Override
    protected void executeUpdate(HttpHeaders headers, UpdateBookRequest request) {
        BookEntity bookEntity = bookRepository.findByAuthor(request.getAuthor());

        if (bookEntity != null) {
            bookRepository.save(bookServiceMapper.mapUpdateRequestToEntity(request, bookEntity));
        } else {
            throw new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null);
        }
    }
}
