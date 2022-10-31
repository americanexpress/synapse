package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoService;
import org.springframework.stereotype.Service;

@Service
public class GetBookService extends BaseGetMonoService<ReadBookResponse> {

    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    protected ReadBookResponse executeRead(String identifier) {
        BookEntity bookEntity = bookRepository.findByTitle(identifier);
        return BookServiceMapper.populateReadBookResponse(bookEntity);
    }
}
