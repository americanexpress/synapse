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
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * {@code DeleteBookService} Deletes a book resource.
 */
@Service
public class DeleteBookService extends BaseDeleteService {

    /**
     * bookRepository used to query the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor taking in and autowiring BookRepository
     * @param bookRepository used to query the database.
     */
    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * executeDelete will be used to delete book resource by title
     * @param title of the book.
     */
    @Override
    protected void executeDelete(HttpHeaders headers, String title) {
        BookEntity bookEntity = bookRepository.findByTitle(title);
        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        } else {
            throw new ApplicationClientException(ErrorCode.NOT_FOUND.getMessage(), ErrorCode.NOT_FOUND, (String[]) null);
        }
    }
}
