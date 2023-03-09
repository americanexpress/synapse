package io.americanexpress.book.repository;

import io.americanexpress.book.entity.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<BookEntity, String> {
}
