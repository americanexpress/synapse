package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.service.book.rest.model.UpdateBookRequest;
import io.americanexpress.service.book.rest.utils.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.BaseUpdateService;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookService extends BaseUpdateService<UpdateBookRequest> {

    private BookRepository bookRepository;

    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeUpdate(UpdateBookRequest request) {
        BookEntity bookEntity = bookRepository.findByTitle(request.getTitle());
        if (bookEntity != null) {
            bookRepository.save(BookServiceMapper.populateBookEntityForUpdate(request));
        }
    }
}
