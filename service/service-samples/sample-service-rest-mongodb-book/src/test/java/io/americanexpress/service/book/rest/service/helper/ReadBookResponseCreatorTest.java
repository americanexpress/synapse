package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code ReadBookResponseCreatorTest} tests the {@link ReadBookResponseCreator} class.
 */
class ReadBookResponseCreatorTest {

    @Test
    void create_givenBookEntity_expectedReadBookResponse() {
        BookEntity book = new BookEntity();
        book.setTitle("Book");
        book.setAuthor("Author");
        ReadBookResponse response = ReadBookResponseCreator.create(book);
        Assertions.assertNotNull(response);
    }

    @Test
    void create_givenNull_expectedNull() {
        ReadBookResponse response = ReadBookResponseCreator.create(null);
        Assertions.assertNull(response);
    }


}
