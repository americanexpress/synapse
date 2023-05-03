package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseCreateReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code CreateBookReactiveService} service layer to create and save book to redis store.
 */
@Service
public class CreateBookReactiveService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    /**
     * Used to save book to redis store.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new Create book reactive service.
     *
     * @param bookRepository the book repository
     */
    public CreateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Creates book from request and saves to redis store.
     *
     * @param headers the httpHeaders
     * @param request the request
     * @return mono CreateBookResponse if save is successful
     */
    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        BookEntity book = new BookEntity(request.getTitle(), request.getAuthor());
        return bookRepository.save(book).map(bookEntity -> new CreateBookResponse());
    }
}
