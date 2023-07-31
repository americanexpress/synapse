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
package io.americanexpress.data.oracle.cp.book.service;

import io.americanexpress.data.oracle.cp.book.dao.BookRepository;
import io.americanexpress.data.oracle.cp.book.entity.BookEntity;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.spi.Connection;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookPersistenceService {

    private final ConnectionPool connectionPool;

    private final BookRepository bookRepository;

    public BookPersistenceService(ConnectionPool connectionPool, BookRepository bookRepository) {
        this.connectionPool = connectionPool;
        this.bookRepository = bookRepository;
    }

    public Mono<BookEntity> executeFindByTitleAndAuthor(String title, String author) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByTitleAndAuthor(title, author),
                Connection::close);
    }

    public Flux<BookEntity> executeFindAll() {
        return Flux.usingWhen(connectionPool.create(),
                connection -> bookRepository.findAll(),
                Connection::close);
    }

    public Mono<BookEntity> executeFindByAuthor(String author) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByAuthor(author),
                Connection::close);
    }

    public Mono<BookEntity> executeFindByTitle(String title) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByTitle(title),
                Connection::close);
    }

    public Mono<Void> executeDeleteByTitle(String title) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.deleteByTitle(title),
                Connection::close);
    }

    public Mono<BookEntity> executeSave(BookEntity bookEntity) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.save(bookEntity),
                Connection::close);
    }
}
