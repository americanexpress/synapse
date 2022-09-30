package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveUpdateService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUpdateBookService extends BaseReactiveUpdateService<UpdateBookRequest> {

    private final BookRepository bookRepository;

    public ReactiveUpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<Void> executeUpdate(UpdateBookRequest request) {
        return bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor())
                .map(book -> updateBook(book, request.getNumberOfCopies()))
                .flatMap(book -> {
                    if(book == null) {
                        return Mono.error(new NotFoundException("Book was not found"));
                    }else {
                        return bookRepository.save(book);
                    }
                }).then();
    }

    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }
}
