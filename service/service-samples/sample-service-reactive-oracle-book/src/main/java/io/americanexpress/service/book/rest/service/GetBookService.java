package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetMonoReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetBookService extends BaseGetMonoReactiveService<ReadBookResponse> {

    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    protected Mono<ReadBookResponse> executeRead(String identifier) {
        return bookRepository.findByTitle(identifier)
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.empty());
    }
}
