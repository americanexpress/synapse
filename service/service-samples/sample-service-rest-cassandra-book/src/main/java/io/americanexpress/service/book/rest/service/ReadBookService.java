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
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * {@code ReadBookService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class ReadBookService extends BaseReadMonoService<ReadBookRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new ReadBookService.
     *
     * @param bookRepository the book repository
     */
    public ReadBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected ReadBookResponse executeRead(HttpHeaders headers, ReadBookRequest request) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        return bookEntity.map(ReadBookResponseCreator::create).orElseGet(() -> ReadBookResponseCreator.create(null));
    }
}
