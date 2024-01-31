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
import io.americanexpress.synapse.data.db2.repository.BaseCrudDb2Repository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * {@code BookRepository} is the dao repository to handle the queries for the books table.
 * @author tisla4
 */
@Repository
public interface BookRepository extends BaseCrudDb2Repository<BookEntity, Long> {

    /**
     * Find book with a specific title.
     * @param title the title of the book
     * @return the book matching the given title
     */
    Optional<BookEntity> findByTitle(String title);

    /**
     * Find book with a specific author.
     * @param author the author of the book
     * @return the book matching the given author
     */
    Optional<BookEntity> findByAuthor(String author);
}
