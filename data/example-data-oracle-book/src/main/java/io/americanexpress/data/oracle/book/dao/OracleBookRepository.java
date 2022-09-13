package io.americanexpress.data.oracle.book.dao;

import io.americanexpress.data.oracle.book.entity.OracleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleBookRepository extends JpaRepository<OracleEntity, Long> {
    OracleEntity findByPublisher(String publisher);
}
