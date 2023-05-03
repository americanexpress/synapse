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
package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.entity.BookEntity;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * {@code BookRepository} is used to access redis store by using {@link ReactiveRedisOperations}.
 * Spring-data-redis does not provide built in reactive repository so need to create access methods using ReactiveRedisOperations.
 */
@Repository
public class BookRepository {

    /**
     * Used to access data in redis store.
     */
    private final ReactiveRedisOperations<String, BookEntity> reactiveRedisOperations;

    /**
     * The constant BOOKS.
     */
    private static final String BOOKS = "books";

    /**
     * Instantiates a new Book repository.
     *
     * @param reactiveRedisOperations the reactive redis operations
     */
    public BookRepository(ReactiveRedisOperations<String, BookEntity> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    /**
     * Find by title.
     *
     * @param title the title
     * @return the mono BookEntity if book exists with title
     */
    public Mono<BookEntity> findByTitle(String title) {
        return reactiveRedisOperations.<String, BookEntity>opsForHash()
                .values(BOOKS)
                .filter(book -> book.getTitle().equals(title))
                .singleOrEmpty();
    }

    /**
     * Save bookEntity to redis store.
     *
     * @param book the bookEntity
     * @return the mono BookEntity if saved successfully
     */
    public Mono<BookEntity> save(BookEntity book) {
        if (book.getIdentifier() == null) {
            String id = UUID.randomUUID().toString();
            book.setIdentifier(id);
        }
        return reactiveRedisOperations.<String,  BookEntity>opsForHash().put(BOOKS, book.getIdentifier(), book)
                .log()
                .map(p -> book);

    }

    /**
     * Find by id.
     *
     * @param id the id of the bookEntity in redis store.
     * @return the mono BookEntity if id found
     */
    public Mono<BookEntity> findById(String id) {
        return reactiveRedisOperations.<String, BookEntity>opsForHash().get(BOOKS, id);
    }

    /**
     * Find all
     *
     * @return the flux of BookEntity
     */
    public Flux<BookEntity> findAll() {
        return this.reactiveRedisOperations.<String, BookEntity>opsForHash().values(BOOKS);
    }
}

