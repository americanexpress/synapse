package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.config.BookDataTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.context.ContextConfiguration;

@DataCassandraTest
@ContextConfiguration(classes = BookDataTestConfig.class)
@AutoConfigurationPackage
public class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }

}
