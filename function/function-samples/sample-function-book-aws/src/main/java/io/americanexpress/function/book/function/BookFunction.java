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

@Configuration
public class BookFunction {

    private final String BOOK_NOT_FOUND = "Book Not Found";

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
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .map(book -> updateBook(book, request.getPayload().getNumberOfCopies()))
                .flatMap(bookRepository::save);
    }

    @Bean
    public Function<Message<UpdateBookRequest>, Mono<CreateBookResponse>> updateBook() {
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .map(book -> updateBook(book, request.getPayload().getNumberOfCopies()))
                .flatMap(bookRepository::save)
                .map(bookEntity -> new CreateBookResponse());
    }

    private BookEntity updateBook(BookEntity book, int numOfCopies){
        if (book != null) book.setNumberOfCopies(numOfCopies);
        return book;
    }

    @Bean
    public Consumer<Message<BookRequest>> delete() {
        return request -> bookRepository.findByTitleAndAuthor(request.getPayload().getTitle(), request.getPayload().getAuthor())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND)))
                .flatMap(bookRepository::delete);
    }

}
