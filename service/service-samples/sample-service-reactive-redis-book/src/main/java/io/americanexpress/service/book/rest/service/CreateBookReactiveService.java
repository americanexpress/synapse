package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseCreateReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateBookReactiveService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    public CreateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<CreateBookResponse> executeCreate(HttpHeaders headers, CreateBookRequest request) {
        BookEntity book = new BookEntity(request.getTitle(), request.getAuthor());
        return bookRepository.save(book)
                .map(bookEntity -> {
                    return new CreateBookResponse();
                });
    }
}
