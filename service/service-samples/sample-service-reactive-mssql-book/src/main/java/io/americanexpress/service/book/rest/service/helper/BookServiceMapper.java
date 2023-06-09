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
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponse;

/**
 * {@code BookServiceMapper} Helps service modules in mapping object.
 */
public class BookServiceMapper {

    /**
     * The private constructor.
     */
    private BookServiceMapper() {}

    /**
     * Populates ReadBookResponse with BookEntity.
     * @param bookEntity Used for mapping bookEntity from database to response object.
     * @return A response object representation of BookEntity.
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
     * @param request Used to create a BookEntity and store it in database.
     * @return A BookEntity object to save to database.
     */
    public static BookEntity populateBookEntityForCreation(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        bookEntity.setCreatedBy(request.getCreatedBy());
        bookEntity.setVersion(1L);

        return bookEntity;
    }

    /**
     * Populates CreateBookResponse with BookEntity.
     * @param bookEntity Used for mapping bookEntity object from database to response object.
     * @return A response object representation of BookEntity.
     */
    public static CreateBookResponse populateCreateBookResponse(BookEntity bookEntity) {
        CreateBookResponse response = new CreateBookResponse();

        response.setId(String.valueOf(bookEntity.getId()));
        response.setAuthor(bookEntity.getAuthor());
        response.setTitle(bookEntity.getTitle());
        response.setCreatedBy(bookEntity.getCreatedBy());

        return response;
    }

}
