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
package io.americanexpress.data.mysql.book.dao;

import io.americanexpress.data.mysql.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@code BookRepository} example of using JPA to find a book entity by id.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Finds A Book Entity in the database by title and author.
     * @param title of the book.
     * @param author of the book.
     * @return A BookEntity of the provided title and author.
     */
    BookEntity findByTitleAndAuthor(String title, String author);

    /**
     * Finds a Book Entity in the database by title.
     * @param title of the book.
     * @return A BookEntity of the provided title.
     */
    BookEntity findByTitle(String title);

}
