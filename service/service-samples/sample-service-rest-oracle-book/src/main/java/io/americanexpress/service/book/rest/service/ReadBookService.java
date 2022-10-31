package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookRequest;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoService;
import org.springframework.stereotype.Service;

@Service
public class ReadBookService extends BaseReadMonoService<ReadBookRequest, ReadBookResponse> {
    private final BookRepository bookRepository;

    public ReadBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected ReadBookResponse executeRead(ReadBookRequest request) {
        BookEntity bookEntity = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        return BookServiceMapper.populateReadBookResponse(bookEntity);
    }
}
