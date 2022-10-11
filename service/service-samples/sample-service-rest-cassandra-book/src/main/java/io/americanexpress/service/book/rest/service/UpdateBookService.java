package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBookService extends BaseUpdateService<UpdateBookRequest> {

    private final BookRepository bookRepository;

    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeUpdate(UpdateBookRequest request) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        bookEntity.ifPresent(book -> {
            book.setNumberOfCopies(request.getNumberOfCopies());
            bookRepository.save(book);
        });
    }
}
