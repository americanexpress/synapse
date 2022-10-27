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
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * {@code UpdateBookService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class UpdateBookService extends BaseUpdateService<UpdateBookRequest> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new UpdateBookService.
     *
     * @param bookRepository the book repository
     */
    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeUpdate(HttpHeaders headers, UpdateBookRequest request) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        bookEntity.ifPresent(book -> {
            book.setNumberOfCopies(request.getNumberOfCopies());
            bookRepository.save(book);
        });
    }
}
