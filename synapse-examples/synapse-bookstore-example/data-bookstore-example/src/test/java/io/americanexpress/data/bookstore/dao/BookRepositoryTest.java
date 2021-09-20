package io.americanexpress.data.bookstore.dao;

import io.americanexpress.data.bookstore.config.DataBookConfig;
import io.americanexpress.data.bookstore.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

/**
 * The type Sample repository test.
 */
@ContextConfiguration(classes = DataBookConfig.class)
class BookRepositoryTest extends BasePostgresRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Find by id given good repo expected success.
     */
    @Test
    void findById_givenGoodRepo_expectedSuccess() {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(1L);
        if (bookEntityOptional.isPresent()) {
            BookEntity bookEntity = bookEntityOptional.get();
            Assertions.assertEquals(1, bookEntity.getIdentifier());
        }
    }
}
