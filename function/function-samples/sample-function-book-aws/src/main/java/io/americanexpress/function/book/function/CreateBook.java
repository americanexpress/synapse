//package io.americanexpress.function.book.function;
//
//import io.americanexpress.data.book.entity.BookEntity;
//import io.americanexpress.data.book.repository.BookRepository;
//import io.americanexpress.function.book.function.helper.BookEntityCreator;
//import io.americanexpress.function.book.model.CreateBookRequest;
//import io.americanexpress.function.book.model.CreateBookResponse;
//import reactor.core.publisher.Mono;
//
//import java.util.function.Function;
//
//public class CreateBook implements Function<CreateBookRequest, Mono<CreateBookResponse>> {
//
//    private final BookRepository bookRepository;
//
//    public CreateBook(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    @Override
//    public Mono<CreateBookResponse> apply(CreateBookRequest request) {
//        BookEntity bookEntity = BookEntityCreator.create(request.getTitle(), request.getAuthor(), 1);
//        return bookRepository.save(bookEntity).map(book -> new CreateBookResponse()).switchIfEmpty(Mono.empty());
//    }
//}
