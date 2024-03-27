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

/**
 * {@code BookPersistenceService} A persistence service layer for managing data using
 * a connection pool for efficient database interactions.
 * The service encapsulates methods to perform all CRUD (Create, Read, Update, & Delete) operations on
 * {@link BookEntity} repository which manges the lifecycle of the underlying database connections.
 * It ensures that connections are acquired, used, and released correctly using a connection pool,
 * allowing for better performance and resource management.
 */
@Service
public class BookPersistenceService {

    private final ConnectionPool connectionPool;

    private final BookRepository bookRepository;

    /**
     * Constructs a new {@code BookPersistenceService} with the provided {@link ConnectionPool}
     * & {@link BookRepository}.
     * @param connectionPool the {@link ConnectionPool} used to manage database connections.
     * @param bookRepository The {@link BookRepository} providing data access for books.
     */
    public BookPersistenceService(ConnectionPool connectionPool, BookRepository bookRepository) {
        this.connectionPool = connectionPool;
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves a {@link BookEntity} by Strings of title and author asynchronously.
     * @param title The title of the book.
     * @param author The author of the book.
     * @return A {@link Mono} emitting the found {@link BookEntity}.
     */
    public Mono<BookEntity> executeFindByTitleAndAuthor(String title, String author) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByTitleAndAuthor(title, author),
                Connection::close);
    }

    /**
     * Retrieves all {@link BookEntity} records asynchronously.
     * @return A {@link Flux} emitting all {@link BookEntity} records in the repository.
     */
    public Flux<BookEntity> executeFindAll() {
        return Flux.usingWhen(connectionPool.create(),
                connection -> bookRepository.findAll(),
                Connection::close);
    }

    /**
     * Retrieves a {@link BookEntity} by author asynchronously.
     * @param author The author of the book.
     * @return A {@link Mono} emitting the found {@link BookEntity}.
     */
    public Mono<BookEntity> executeFindByAuthor(String author) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByAuthor(author),
                Connection::close);
    }

    /**
     * Retrieves a {@link BookEntity} by title asynchronously.
     * @param title The title of the book.
     * @return A {@link Mono} emitting the found {@link BookEntity}.
     */
    public Mono<BookEntity> executeFindByTitle(String title) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.findByTitle(title),
                Connection::close);
    }

    /**
     * Deletes a {@link BookEntity} by title asynchronously.
     * @param title The title of the book to delete.
     * @return A {@link Mono<Void>} that completes when the delete operation is done.
     */
    public Mono<Void> executeDeleteByTitle(String title) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.deleteByTitle(title),
                Connection::close);
    }

    /**
     * Saves a {@link BookEntity} asynchronously.
     * @param bookEntity The {@link BookEntity} to save.
     * @return A {@link Mono} emitting the saved {@link BookEntity}.
     */
    public Mono<BookEntity> executeSave(BookEntity bookEntity) {
        return Mono.usingWhen(connectionPool.create(),
                connection -> bookRepository.save(bookEntity),
                Connection::close);
    }
}
