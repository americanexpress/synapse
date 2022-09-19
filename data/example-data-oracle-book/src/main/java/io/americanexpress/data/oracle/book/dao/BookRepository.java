package io.americanexpress.data.oracle.book.dao;

import io.americanexpress.data.oracle.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByPublisher(String publisher);
}
