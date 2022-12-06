package io.americanexpress.data.mysql.book.test;

import io.americanexpress.data.mysql.book.dao.BookRepository;
import io.americanexpress.data.mysql.book.entity.BookEntity;
import io.americanexpress.data.mysql.book.test.config.MySqlDataConfigTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MySqlDataConfigTest.class)
public class MySqlDataBookIT {
    /**
     * BookRepository used for database access.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void findById_givenValidId_expectedBookFound() {
        bookRepository.findByTitle("Synapse").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void saveNewTitle_givenValidId_expectedBookFound() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("author");
        bookEntity.setTitle("Hello World");
        bookRepository.save(bookEntity).subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void updateTitle_givenValidEntity_expectedBookFound() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor("John Doe");
        bookEntity.setTitle("Hello World");
        bookRepository.save(bookEntity).subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNotNull);
    }

    /**
     * Test and finds Synapse record in book table.
     */
    @Test
    public void deleteTitle_givenValidTitle_expectedBookNotFound() {
        bookRepository.deleteByTitle("Hello World").subscribe();
        bookRepository.findByTitle("Hello World").subscribe(Assertions::assertNull);
    }


}
