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
package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code ReadBookResponseCreatorTest} tests the {@link ReadBookResponseCreator} class.
 */
class ReadBookResponseCreatorTest {

    @Test
    void create_givenBookEntity_expectedReadBookResponse() {
        BookEntity book = new BookEntity();
        book.setTitle("Book");
        book.setAuthor("Author");
        ReadBookResponse response = ReadBookResponseCreator.create(book);
        Assertions.assertNotNull(response);
    }

    @Test
    void create_givenNull_expectedNull() {
        ReadBookResponse response = ReadBookResponseCreator.create(null);
        Assertions.assertNull(response);
    }


}
