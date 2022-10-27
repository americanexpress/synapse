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
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * {@code DeleteBookService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class DeleteBookService extends BaseDeleteService {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new DeleteBookService.
     *
     * @param bookRepository the book repository
     */
    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeDelete(HttpHeaders headers, String title) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findByTitle(title);
        bookEntityOptional.ifPresent(bookRepository::delete);
    }
}
