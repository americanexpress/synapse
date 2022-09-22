package io.americanexpress.data.book.dao;

import io.americanexpress.data.book.entity.BookDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    void findAll_givenBookEntry_expectedOneBookFound() {
        saveSampleBook();
        Assertions.assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    void findByTitle_givenBookTitle_expectedBookFound() {
        saveSampleBook();
        BookDocument found = bookRepository.findByTitle("Alice in Wonderland");
        Assertions.assertAll("Find by title",
                () -> Assertions.assertNotNull(found),
                () -> Assertions.assertEquals("Alice in Wonderland", found.getTitle()));
    }

    void saveSampleBook() {
        BookDocument bookDocument = new BookDocument();
        bookDocument.setTitle("Alice in Wonderland");
        bookDocument.setAuthor("Lewis Carroll");
        bookRepository.save(bookDocument);
    }
}
