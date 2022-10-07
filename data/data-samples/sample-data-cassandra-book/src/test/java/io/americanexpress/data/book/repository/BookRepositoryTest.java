package io.americanexpress.data.book.repository;

import com.github.nosan.embedded.cassandra.test.spring.EmbeddedCassandra;
import io.americanexpress.data.book.config.BookDataTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = BookDataTestConfig.class)
@ExtendWith(SpringExtension.class)
@EmbeddedCassandra(scripts = "/schema.cql")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll_givenEmptyBookCollection_expectedNoBooksFound() {
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }
}
