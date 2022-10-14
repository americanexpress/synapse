package io.americanexpress.service.book.rest.service.helper;

import io.americanexpress.data.book.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code BookEntityCreatorTest} tests the {@link BookEntityCreator} class.
 */
public class BookEntityCreatorTest {

    @Test
    void create_givenTitleAndAuthor_expectedBookEntity() {
        BookEntity bookEntity = BookEntityCreator.create("Book", "Author", 1);
        Assertions.assertNotNull(bookEntity);
    }

    @Test
    void create_givenNullTitleAndAuthor_expectedBookEntity() {
        BookEntity bookEntity = BookEntityCreator.create(null, null, 1);
        Assertions.assertNotNull(bookEntity);
    }
}
