/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
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

/**
 * {@code BookFunction} contains functions for crud operations /reactive/book.
 */
@Configuration
public class BookFunction {

    private final String BOOK_NOT_FOUND = "Book Not Found";

    /**
     * Used for crud operations on book table in cassandra database.
     */
    private final BookRepository bookRepository;

    /**
     * Instantiates a new Book function.
     *
     * @param bookRepository the book repository
     */
    public BookFunction(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Create function.
     *
     * @return the function
     */
    @Bean
    public Function<Message<CreateBookRequest>, Mono<CreateBookResponse>> create() { //406
        return request -> bookRepository.save(BookEntityCreator.create(request.getPayload().getTitle(), request.getPayload().getAuthor(), 1))
                .map(bookEntity -> new CreateBookResponse());
    }

    /**
     * Create book consumer.
     *
     * @return the consumer
     */
    @Bean
    public Consumer<Message<CreateBookRequest>> createBook() { //202
        return request -> bookRepository.save(BookEntityCreator.create(request.getPayload().getTitle(), request.getPayload().getAuthor(), 1));
    }

    /**
     * Read function.
     *
     * @return the function
     */
    @Bean
    public Function<Message<ReadBookRequest>, Mono<ReadBookResponse>> read() { //200
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .map(ReadBookResponseCreator::create);
    }

    /**
     * Get supplier.
     *
     * @return the supplier
     */
    @Bean
    public Supplier<Flux<ReadBookResponse>> get() { //200
        return () -> bookRepository.findAll().map(ReadBookResponseCreator::create);
    }

    /**
     * Update consumer.
     *
     * @return the consumer
     */
    @Bean
    public Consumer<Message<UpdateBookRequest>> update() { //202
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .map(book -> updateBook(book, request.getPayload().getNumberOfCopies()))
                .flatMap(bookRepository::save);
    }

    /**
     * Update book function.
     *
     * @return the function
     */
    @Bean
    public Function<Message<UpdateBookRequest>, Mono<CreateBookResponse>> updateBook() { //406
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .map(book -> updateBook(book, request.getPayload().getNumberOfCopies()))
                .flatMap(bookRepository::save)
                .map(bookEntity -> new CreateBookResponse());
    }

    /**
     * Update book entity given with number of copies.
     *
     * @param book the bookEntity to be updated
     * @param numOfCopies the number of copies
     * @return the bookEntity created
     */
    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }

    /**
     * Delete consumer.
     *
     * @return the consumer
     */
    @Bean
    public Consumer<Message<BookRequest>> delete() { //202
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .flatMap(bookRepository::delete);
    }

}
