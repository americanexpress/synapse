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

@Repository
public class BookRepository {

    private final ReactiveRedisOperations<String, BookEntity> reactiveRedisOperations;

    private final String BOOKS = "books";

    public BookRepository(ReactiveRedisOperations<String, BookEntity> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    public Mono<BookEntity> findByTitle(String title) {
        return reactiveRedisOperations.<String, BookEntity>opsForHash()
                .values(BOOKS)
                .filter(book -> book.getTitle().equals(title))
                .singleOrEmpty();
    }
    public Mono<BookEntity> save(BookEntity book) {
        if (book.getIdentifier() == null) {
            String id = UUID.randomUUID().toString();
            book.setIdentifier(id);
        }
        return reactiveRedisOperations.<String,  BookEntity>opsForHash().put(BOOKS, book.getIdentifier(), book)
                .log()
                .map(p -> book);

    }

    public Mono<BookEntity> findById(String id) {
        return reactiveRedisOperations.<String, BookEntity>opsForHash().get(BOOKS, id);
    }

    public Flux<BookEntity> findAll() {
        return this.reactiveRedisOperations.<String, BookEntity>opsForHash().values(BOOKS);
    }
}

