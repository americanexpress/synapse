package io.americanexpress.data.book.dao;

import io.americanexpress.data.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BookRepository is the dao repository to handle the queries for the book table.
 */
@Repository
interface BookRepository extends JpaRepository<BookEntity, Long> {
}
