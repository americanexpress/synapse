package io.americanexpress.service.book.rest.utils;

import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
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
        return bookEntity;
    }

    public static CreateBookResponse populateCreateBookResponse(BookEntity bookEntity) {
        CreateBookResponse response = new CreateBookResponse();

        response.setId(String.valueOf(bookEntity.getId()));
        response.setAuthor(bookEntity.getAuthor());
        response.setTittle(bookEntity.getTitle());

        return response;
    }

    public static BookEntity populateBookEntityForUpdate(BookEntity request) {
        BookEntity bookEntity = new BookEntity();

        if (StringUtils.isNotBlank(request.getAuthor())) {
            bookEntity.setAuthor(request.getAuthor());
        }

        if (StringUtils.isNotBlank(request.getTitle())) {
            bookEntity.setTitle(request.getTitle());
        }

        return bookEntity;
    }
}
