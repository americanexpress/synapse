package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.dao.BookRepository;
import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactiveCreateMonoBookService extends BaseCreateService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    @Autowired
    public ReactiveCreateMonoBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected CreateBookResponse executeCreate(CreateBookRequest request) {
        CreateBookResponse createBookResponse = null;

        BookEntity book = new BookEntity();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());

        if(bookRepository.save(book).block() != null) {
            createBookResponse =  new CreateBookResponse();
        }

        return createBookResponse;
    }
}
