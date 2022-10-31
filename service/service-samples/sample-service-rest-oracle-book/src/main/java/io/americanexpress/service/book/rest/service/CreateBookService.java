package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.stereotype.Service;

@Service
public class CreateBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    protected CreateBookResponse executeCreate(CreateBookRequest request) {
        BookEntity bookEntity = bookRepository.save(BookServiceMapper.populateBookEntityForCreation(request));
        return BookServiceMapper.populateCreateBookResponse(bookEntity);
    }
}
