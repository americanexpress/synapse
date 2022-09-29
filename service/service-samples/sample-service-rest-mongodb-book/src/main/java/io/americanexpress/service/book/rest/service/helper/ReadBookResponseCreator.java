package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import org.springframework.stereotype.Component;

@Component
public class ReadBookResponseCreator {

    public ReadBookResponse create(BookEntity book) {
        ReadBookResponse response = null;
        if(book != null) {
            response = new ReadBookResponse();
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
        }
        return response;
    }
}
