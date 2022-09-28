package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.config.BookDataTestConfig;
import io.americanexpress.data.book.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * {@code BookRepositoryIT} class runs integration test on local MongoDB instance test database.
 */
@ContextConfiguration(classes = BookDataTestConfig.class)
@DataMongoTest
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        bookRepository.deleteAll().block();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Flux<BookEntity> bookDocumentFlux = bookRepository.findAll();
        StepVerifier.create(bookDocumentFlux).expectNextCount(0).verifyComplete();
    }

    @Test
    void findAll_givenBookCollection_expectedCollectionNotEmpty() {
        BookEntity entry = createSampleBook();
        bookRepository.save(entry).block();
        Flux<BookEntity> bookDocumentFlux = bookRepository.findAll();
        StepVerifier.create(bookDocumentFlux).expectNextCount(1).verifyComplete();
    }

    @Test
    void findByTitle_givenBookTitle_expectedBookFound() {
        BookEntity entry = createSampleBook();
        bookRepository.save(entry).block();
        Flux<BookEntity> bookDocumentFlux = bookRepository.findByTitle("Alice in Wonderland");
        StepVerifier.create(bookDocumentFlux).assertNext(bookEntity -> entry.getTitle().equalsIgnoreCase(bookEntity.getTitle())).expectComplete().verify();
    }

    private BookEntity createSampleBook() {
        BookEntity bookDocument = new BookEntity();
        bookDocument.setTitle("Alice in Wonderland");
        bookDocument.setAuthor("Lewis Carroll");
        return bookDocument;
    }
}
