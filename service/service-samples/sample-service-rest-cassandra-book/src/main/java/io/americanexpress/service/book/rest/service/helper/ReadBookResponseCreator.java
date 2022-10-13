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

/**
 * {@code ReadBookResponseCreator} is the helper class for creating a ReadBookResponse.
 */
public class ReadBookResponseCreator {

    /**
     * Create read book response.
     *
     * @param bookEntity the book entity
     * @return the read book response
     */
    public static ReadBookResponse create(BookEntity bookEntity) {
        ReadBookResponse response = null;
        if(bookEntity != null) {
            response = new ReadBookResponse();
            response.setTitle(bookEntity.getTitle());
            response.setAuthor(bookEntity.getAuthor());
            response.setNumberOfBooks(bookEntity.getNumberOfCopies());
        }
        return response;
    }
}
