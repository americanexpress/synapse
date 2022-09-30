package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveDeleteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDeleteBookService extends BaseReactiveDeleteService {

    private final BookRepository bookRepository;

    public ReactiveDeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<Void> executeDelete(String identifier) {
        //assuming identifier = title of book
        return bookRepository.deleteByTitle(identifier);
    }
}
