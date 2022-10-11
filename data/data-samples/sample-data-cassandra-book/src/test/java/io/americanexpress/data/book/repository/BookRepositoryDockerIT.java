package io.americanexpress.data.book.repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import io.americanexpress.data.book.config.BookDataTestConfig;
import io.americanexpress.data.book.entity.BookEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

@ContextConfiguration(classes = BookDataTestConfig.class)
@Testcontainers
@DataCassandraTest
@EnableAutoConfiguration
class BookRepositoryDockerIT {

    @Autowired
    private BookRepository bookRepository;

    @Container
    public static final CassandraContainer cassandra = (CassandraContainer) new CassandraContainer("cassandra:4.1").withExposedPorts(9042);

    @BeforeAll
    static void setupCassandraConnectionProperties() {
        System.setProperty("spring.data.cassandra.keyspace-name", "synapse");
        System.setProperty("spring.data.cassandra.contact-points", "127.0.0.1");
        System.setProperty("spring.data.cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));

        createKeyspace(cassandra.getCluster());
    }

    private static void createKeyspace(Cluster cluster) {
        try (Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS synapse" +
                    " WITH replication = \n" +
                    "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }

    @BeforeEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Test
    void save_givenBookEntity_expectedBookSaved() {
        BookEntity bookEntity = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        bookEntity.setIdentifier(UUID.randomUUID());

        BookEntity result = bookRepository.save(bookEntity);
        Assertions.assertNotNull(result);
    }

    @Test
    void findByTitleAndAuthor_givenBookDoesNotExist_expectedNull() {
        Optional<BookEntity> result = bookRepository.findByTitleAndAuthor("Alice In Wonderland", "Lewis Carroll");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void findByTitleAndAuthor_givenBookEntity_expectedBookFound() {
        BookEntity bookEntity = new BookEntity("Alice In Wonderland", "Lewis Carroll");
        bookEntity.setIdentifier(UUID.randomUUID());
        bookRepository.save(bookEntity);

        Optional<BookEntity> result = bookRepository.findByTitleAndAuthor("Alice In Wonderland", "Lewis Carroll");
        Assertions.assertEquals(bookEntity.getTitle(), result.get().getTitle());
    }
}
