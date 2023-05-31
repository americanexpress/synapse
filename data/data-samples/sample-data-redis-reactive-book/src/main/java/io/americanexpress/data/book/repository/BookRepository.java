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
import io.americanexpress.synapse.data.redis.repository.BaseRedisHashReactiveRepository;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * {@code BookRepository} is used to access redis store.
 */
@Repository
public class BookRepository extends BaseRedisHashReactiveRepository<String, BookEntity> {
    protected BookRepository(ReactiveRedisOperations<String, BookEntity> reactiveRedisOperations) {
        super(reactiveRedisOperations);
    }

    @Override
    public String getKey() {
        return "books";
    }

    /**
     * Find by title.
     *
     * @param title the title
     * @return the mono BookEntity if book exists with title
     */
    public Mono<BookEntity> findByTitle(String title) {
        return reactiveRedisOperations.<String, BookEntity>opsForHash()
                .values(getKey())
                .filter(book -> book.getTitle().equals(title))
                .singleOrEmpty();
    }
}

