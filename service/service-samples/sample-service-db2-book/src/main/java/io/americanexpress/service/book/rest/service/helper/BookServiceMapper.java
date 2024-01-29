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
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class BookServiceMapper {

    private final Random random;

    /**
     * Private constructor for utility class.
     */
    public BookServiceMapper() {
        random = new Random();
    }

    public BookEntity mapCreateRequestToEntity(CreateBookRequest createBookRequest) {
        BookEntity bookEntity = null;

        if (createBookRequest != null) {
            bookEntity = new BookEntity();
            bookEntity.setId(random.nextLong(1000));
            bookEntity.setAuthor(createBookRequest.getAuthor());
            bookEntity.setTitle(createBookRequest.getTitle());
            bookEntity.setDescription(createBookRequest.getDescription());
        }

        return bookEntity;
    }

    public CreateBookResponse mapCreateBookResponse(BookEntity bookEntity) {
        CreateBookResponse createBookResponse = null;

        if (bookEntity != null) {
            createBookResponse = new CreateBookResponse();
            createBookResponse.setId(String.valueOf(bookEntity.getId()));
        }

        return createBookResponse;
    }

    public ReadBookResponse mapEntityToReadResponse(BookEntity bookEntity) {
        ReadBookResponse readBookResponse = null;

        if (bookEntity != null) {
            readBookResponse = new ReadBookResponse();
            readBookResponse.setId(Long.toString(bookEntity.getId()));
            readBookResponse.setAuthor(bookEntity.getAuthor());
            readBookResponse.setTitle(bookEntity.getTitle());
            readBookResponse.setDescription(bookEntity.getDescription());
        }

        return readBookResponse;
    }

    public BookEntity mapUpdateRequestToEntity(UpdateBookRequest updateBookRequest, BookEntity bookEntity) {

        if (StringUtils.isNotBlank(updateBookRequest.getAuthor())) {
            bookEntity.setAuthor(updateBookRequest.getAuthor());
        }
        if (StringUtils.isNotBlank(updateBookRequest.getTitle())) {
            bookEntity.setTitle(updateBookRequest.getTitle());
        }
        if (StringUtils.isNotBlank(updateBookRequest.getDescription())) {
            bookEntity.setDescription(updateBookRequest.getDescription());
        }

        return bookEntity;
    }
}
