package io.americanexpress.function.book.function;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.function.book.function.helper.BookEntityCreator;
import io.americanexpress.function.book.function.helper.ReadBookResponseCreator;
import io.americanexpress.function.book.model.BookRequest;
import io.americanexpress.function.book.model.CreateBookRequest;
import io.americanexpress.function.book.model.CreateBookResponse;
import io.americanexpress.function.book.model.ReadBookRequest;
import io.americanexpress.function.book.model.ReadBookResponse;
import io.americanexpress.function.book.model.UpdateBookRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class BookFunction {

    private final BookRepository bookRepository;

    public BookFunction(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public Function<Message<CreateBookRequest>, Mono<CreateBookResponse>> create() {
        return request -> bookRepository.save(BookEntityCreator.create(request.getPayload().getTitle(), request.getPayload().getAuthor(), 1))
                    .map(bookEntity -> new CreateBookResponse());
    }

    @Bean
    public Consumer<Message<CreateBookRequest>> createBook() {
        return request -> bookRepository.save(BookEntityCreator.create(request.getPayload().getTitle(), request.getPayload().getAuthor(), 1));
    }

    @Bean
    public Function<Message<ReadBookRequest>, Mono<ReadBookResponse>> read() {
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .map(ReadBookResponseCreator::create);
    }

    @Bean
    public Supplier<Flux<ReadBookResponse>> get() {
        return () -> bookRepository.findAll().map(ReadBookResponseCreator::create);
    }

    @Bean
    public Consumer<Message<UpdateBookRequest>> update() {
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found")))
                .map(book -> updateBook(book, request.getPayload().getNumberOfCopies()))
                .flatMap(bookRepository::save);
    }

    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }

    @Bean
    public Consumer<Message<BookRequest>> delete() {
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found")))
                .flatMap(bookRepository::delete);
    }

}
