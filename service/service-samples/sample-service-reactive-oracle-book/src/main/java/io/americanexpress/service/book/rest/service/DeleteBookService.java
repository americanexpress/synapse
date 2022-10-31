package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.synapse.service.rest.service.reactive.BaseDeleteReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class DeleteBookService extends BaseDeleteReactiveService {

    private final BookRepository bookRepository;

    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    protected Mono<Void> executeDelete(String id) {
        return bookRepository.findByTitle(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found")))
                .flatMap(bookRepository::delete)
                .then();
    }
}
