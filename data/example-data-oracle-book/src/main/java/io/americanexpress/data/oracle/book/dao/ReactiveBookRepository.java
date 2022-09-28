package io.americanexpress.data.oracle.book.dao;

import io.americanexpress.data.oracle.book.entity.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveBookRepository extends ReactiveCrudRepository<BookEntity, Long> {
    Mono<BookEntity> findById(long id);
    Flux<BookEntity> findAllById(long id);
}
