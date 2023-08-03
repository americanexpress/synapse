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

import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.data.oracle.book.entity.BookSPEntity;
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
     * Populates ReadBookResponse with BookEntity
     *
     * @param bookEntity
     * @return
     */
    public static ReadBookResponse populateReadBookResponse(BookSPEntity bookEntity) {
        ReadBookResponse res = new ReadBookResponse();
        res.setAuthor(bookEntity.getAuthor());
        res.setId(String.valueOf(bookEntity.getId()));
        res.setTitle(bookEntity.getTitle());
        res.setCreatedBy(bookEntity.getCreatedBy());
        return res;
    }

    /**
     * Populates BookEntity with CreateBookRequest
     *
     * @param request
     * @return
     */
    public static BookEntity populateBookEntityForCreation(CreateBookRequest request) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setTitle(request.getTitle());
        return bookEntity;
    }

    /**
     * Populates CreateBookResponse with BookEntity
     *
     * @param bookEntity
     * @return
     */
    public static CreateBookResponse populateCreateBookResponse(BookSPEntity bookEntity) {
        CreateBookResponse response = new CreateBookResponse();

        response.setId(String.valueOf(bookEntity.getId()));
        response.setAuthor(bookEntity.getAuthor());
        response.setTitle(bookEntity.getTitle());

        return response;
    }

    /**
     * Populate BookEntity with updated request
     *
     * @param request
     * @return
     */
    public static BookEntity populateBookEntityForUpdate(UpdateBookRequest request, BookEntity bookEntity) {

        if (StringUtils.isNotBlank(request.getAuthor())) {
            bookEntity.setAuthor(request.getAuthor());
        }

        bookEntity.setCreatedBy(request.getCreatedBy());

        return bookEntity;
    }

//    public static CreateBookResponse populateCreateBookResponseByResultSet(ResultSet[] resultSets) {
//        return Arrays.stream(resultSets)
//                .filter(Objects::nonNull)
//                .map(res -> {
//                    CreateBookResponse createBookResponse = new CreateBookResponse();
//                    try {
//                        createBookResponse.setTitle(res.getString(res.findColumn("Title")));
//                        createBookResponse.setAuthor(res.getString(res.findColumn("Author")));
//                        createBookResponse.setCreatedBy(res.getString(res.findColumn("Created_By")));
//                    } catch (SQLException e) {
//                        throw new ApplicationClientException("Error processing data.", ErrorCode.GENERIC_5XX_ERROR, (String[]) null);
//                    }
//                    return createBookResponse;
//                })
//                .findFirst()
//                .orElse(null);
//    }
}
