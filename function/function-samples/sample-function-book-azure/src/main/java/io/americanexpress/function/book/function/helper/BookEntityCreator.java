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
package io.americanexpress.function.book.function.helper;

import io.americanexpress.data.book.entity.BookEntity;

/**
 * {@code BookEntityCreator} is the helper class for creating a {@link BookEntity} object.
 */
public class BookEntityCreator {

    private BookEntityCreator() {}

    /**
     * Create book entity.
     *
     * @param title          the title
     * @param author         the author
     * @param numberOfCopies the number of copies
     * @return the book entity
     */
    public static BookEntity create(String title, String author, int numberOfCopies) {
        BookEntity book = new BookEntity(title, author);
        book.setNumberOfCopies(numberOfCopies);
        return book;
    }
}
