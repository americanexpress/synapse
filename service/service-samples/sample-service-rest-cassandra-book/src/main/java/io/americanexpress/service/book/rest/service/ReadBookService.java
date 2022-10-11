package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.ReadBookResponseCreator;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadBookService extends BaseReadMonoService<ReadBookRequest, ReadBookResponse> {

    private final BookRepository bookRepository;

    public ReadBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected ReadBookResponse executeRead(ReadBookRequest request) {
        Optional<BookEntity> bookEntity = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        return bookEntity.map(ReadBookResponseCreator::create).orElseGet(() -> ReadBookResponseCreator.create(null));
    }
}
