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

import io.americanexpress.data.mysql.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * {@code BookServiceMapper} Helps service modules in mapping object.
 */
public class BookServiceMapper {

    /**
     * Populates ReadBookResponse with BookEntity.
     * @param bookEntity A book object from the database.
     * @return A service response representation of BookEntity.
     */
    public static ReadBookResponse populateReadBookResponse(BookEntity bookEntity) {
        ReadBookResponse response = new ReadBookResponse();

        response.setAuthor(bookEntity.getAuthor());
        response.setId(String.valueOf(bookEntity.getId()));
        response.setTitle(bookEntity.getTitle());
        response.setCreatedBy(bookEntity.getCreatedBy());

        return response;
    }

    /**
     * Populates BookEntity with CreateBookRequest.
     * @param request Request object that will be used to create a new BookEntity.
     * @return BookEntity object that will be stored in the database.
     */
    public static BookEntity populateBookEntityForCreation(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        bookEntity.setCreatedBy(request.getCreatedBy());
        return bookEntity;
    }

    /**
     * Populates CreateBookResponse with BookEntity.
     * @param bookEntity A BookEntity object from the database.
     * @return A service response representation of BookEntity.
     */
    public static CreateBookResponse populateCreateBookResponse(BookEntity bookEntity) {
        CreateBookResponse response = new CreateBookResponse();

        response.setId(String.valueOf(bookEntity.getId()));
        response.setAuthor(bookEntity.getAuthor());
        response.setTitle(bookEntity.getTitle());

        return response;
    }

    /**
     * Populate BookEntity with updated request.
     * @param request Will be used for updating a BookEntity.
     * @param bookEntity Is used for determining if BookEntity needs to be updated.
     * @return A BookEntity to update in the database.
     */
    public static BookEntity populateBookEntityForUpdate(UpdateBookRequest request, BookEntity bookEntity) {

        if (StringUtils.isNotBlank(request.getAuthor())) {
            bookEntity.setAuthor(request.getAuthor());
        }

        bookEntity.setCreatedBy(request.getCreatedBy());

        return bookEntity;
    }
}
