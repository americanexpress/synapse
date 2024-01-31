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
import io.americanexpress.service.book.rest.model.CreateBookServiceRequest;
import io.americanexpress.service.book.rest.model.CreateBookServiceResponse;
import io.americanexpress.service.book.rest.model.ReadBookServiceResponse;
import io.americanexpress.service.book.rest.model.UpdateBookServiceRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.Random;

/**
 * {@code BookServiceMapper} maps entities to service request / responses & vice versa.
 */
@Component
public class BookServiceMapper {

    /**
     * Random number generator for the id column.
     */
    private final Random random;

    /**
     * Private constructor for utility class.
     */
    public BookServiceMapper() {
        random = new Random();
    }

    /**
     * Maps the {@link CreateBookServiceRequest} to a {@link BookEntity}.
     * @param createBookRequest the {@link CreateBookServiceRequest}.
     * @return a new {@link BookEntity}.
     */
    public BookEntity mapCreateRequestToEntity(CreateBookServiceRequest createBookRequest) {
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

    /**
     * Maps the {@link BookEntity} to a {@link CreateBookServiceResponse}.
     * @param bookEntity the {@link BookEntity}.
     * @return a new {@link CreateBookServiceResponse}.
     */
    public CreateBookServiceResponse mapCreateBookResponse(BookEntity bookEntity) {
        CreateBookServiceResponse createBookResponse = null;

        if (bookEntity != null) {
            createBookResponse = new CreateBookServiceResponse();
            createBookResponse.setId(String.valueOf(bookEntity.getId()));
        }

        return createBookResponse;
    }

    /**
     * Maps the {@link BookEntity} to a {@link ReadBookServiceResponse}.
     * @param bookEntity the {@link BookEntity}.
     * @return a new {@link ReadBookServiceResponse}.
     */
    public ReadBookServiceResponse mapEntityToReadResponse(BookEntity bookEntity) {
        ReadBookServiceResponse readBookResponse = null;

        if (bookEntity != null) {
            readBookResponse = new ReadBookServiceResponse();
            readBookResponse.setId(Long.toString(bookEntity.getId()));
            readBookResponse.setAuthor(bookEntity.getAuthor());
            readBookResponse.setTitle(bookEntity.getTitle());
            readBookResponse.setDescription(bookEntity.getDescription());
        }

        return readBookResponse;
    }

    /**
     * Maps the {@link UpdateBookServiceRequest} to a {@link BookEntity}.
     * @param updateBookRequest the {@link UpdateBookServiceRequest}.
     * @param bookEntity the {@link BookEntity}.
     * @return the updated {@link BookEntity}.
     */
    public BookEntity mapUpdateRequestToEntity(UpdateBookServiceRequest updateBookRequest, BookEntity bookEntity) {

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
