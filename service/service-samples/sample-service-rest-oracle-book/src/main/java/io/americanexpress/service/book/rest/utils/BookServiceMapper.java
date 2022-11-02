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
package io.americanexpress.service.book.rest.utils;

import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import org.apache.commons.lang3.StringUtils;

public class BookServiceMapper {

    public static ReadBookResponse populateReadBookResponse(BookEntity bookEntity) {
        ReadBookResponse response = new ReadBookResponse();

        response.setAuthor(bookEntity.getAuthor());
        response.setId(String.valueOf(bookEntity.getId()));
        response.setTittle(bookEntity.getTitle());
        response.setCreatedBy(bookEntity.getCreatedBy());

        return response;
    }

    public static BookEntity populateBookEntityForCreation(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        bookEntity.setCreatedBy(request.getCreatedBy());
        return bookEntity;
    }

    public static CreateBookResponse populateCreateBookResponse(BookEntity bookEntity) {
        CreateBookResponse response = new CreateBookResponse();

        response.setId(String.valueOf(bookEntity.getId()));
        response.setAuthor(bookEntity.getAuthor());
        response.setTittle(bookEntity.getTitle());

        return response;
    }

    public static BookEntity populateBookEntityForUpdate(UpdateBookRequest request, BookEntity bookEntity) {

        if (StringUtils.isNotBlank(request.getAuthor())) {
            bookEntity.setAuthor(request.getAuthor());
        }

        bookEntity.setCreatedBy(request.getCreatedBy());

        return bookEntity;
    }
}
