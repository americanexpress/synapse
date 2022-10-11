package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;

import java.util.UUID;

public class BookEntityCreator {

    public static BookEntity create(String title, String author, int numberOfCopies) {
        BookEntity book = new BookEntity(title, author);
        book.setIdentifier(UUID.randomUUID());
        book.setNumberOfCopies(numberOfCopies);
        return book;
    }
}
