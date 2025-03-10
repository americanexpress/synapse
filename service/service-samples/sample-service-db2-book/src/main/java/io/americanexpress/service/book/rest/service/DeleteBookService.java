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
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code DeleteBookService} deletes a book from the database.
 */
@Service
public class DeleteBookService extends BaseDeleteService {

    /**
     * The book repository for querying the database.
     */
    private final BookRepository bookRepository;

    /**
     * Creates a new instance of {@code DeleteBookService} with the specified values.
     * @param bookRepository the repository for the database.
     */
    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Deletes a book from the database.
     * @param headers the headers.
     * @param id      the book's id from the url.
     */
    @Override
    protected void executeDelete(HttpHeaders headers, String id) {
        BookEntity bookEntity = bookRepository.findById(Long.valueOf(id)).orElse(null);

        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        } else {
            throw new ApplicationClientException("Book Not Found.", ErrorCode.GENERIC_4XX_ERROR, (String[]) null);
        }
    }
}
