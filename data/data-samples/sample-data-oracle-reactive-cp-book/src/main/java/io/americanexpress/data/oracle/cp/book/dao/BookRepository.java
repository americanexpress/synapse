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
package io.americanexpress.data.oracle.cp.book.dao;

import io.americanexpress.data.oracle.cp.book.entity.BookEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code BookRepository} example of using JPA to find a book entity by id.
 */
@Repository
public interface BookRepository extends R2dbcRepository<BookEntity, Long> {

    /**
     * Finds a BookEntity based off String parameters of title and author.
     * @param title The title of the book.
     * @param author The author of the book.
     * @return A {@link BookEntity} of the provided Strings title and author.
     */
    Mono<BookEntity> findByTitleAndAuthor(String title, String author);

    /**
     * Finds all BookEntity in the database.
     * @return All {@link BookEntity} in the database.
     */
    Flux<BookEntity> findAll();

    /**
     * Finds a BookEntity by title.
     * @param title The title of the book.
     * @return A {@link BookEntity} of the provided String, title.
     */
    Mono<BookEntity> findByTitle(String title);

    /**
     * Finds a BookEntity by author.
     * @param author The author of the book.
     * @return A {@link BookEntity} of the provided String, author.
     */
    Mono<BookEntity> findByAuthor(String author);

    /**
     * Deletes a BookEntity by title.
     * @param title The title of the book.
     * @return VOID
     */
    Mono<Void> deleteByTitle(String title);

}
