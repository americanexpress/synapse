package io.americanexpress.data.book.repository;

import io.americanexpress.data.book.entity.BookEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<BookEntity, Long> {
}
