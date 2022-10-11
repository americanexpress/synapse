package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;

public class ReadBookResponseCreator {

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
