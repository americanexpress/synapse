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
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Book repository.
 */
@Repository
public interface BookRepository extends R2dbcRepository<BookEntity, Long> {

    /**
     * Find books by title.
     * @param title the book title
     * @return mono book entity
     */
    Mono<BookEntity> findByTitle(String title);


    /**
     * Example of using stored procedure to get books by author.
     * @param author the author
     * @return flux of books
     */
    @Procedure("GET_BOOKS_BY_AUTHOR")
    Flux<BookEntity> getBooksByAuthor(String author);
}
