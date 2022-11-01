package io.americanexpress.function.book.function;

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.function.book.function.helper.BookEntityCreator;
import io.americanexpress.function.book.model.CreateBookRequest;
import io.americanexpress.function.book.model.CreateBookResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class BookFunction {

    private final BookRepository bookRepository;

    public BookFunction(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Bean
    public Function<CreateBookRequest, Mono<CreateBookResponse>> create() {
        return request -> bookRepository.save(BookEntityCreator.create(request.getTitle(), request.getAuthor(), 1))
                    .map(bookEntity -> new CreateBookResponse());
    }
}
