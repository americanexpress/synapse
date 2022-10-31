package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.data.oracle.book.entity.BookEntity;
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookService extends BaseDeleteService {

    private final BookRepository bookRepository;

    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    protected void executeDelete(String id) {
        BookEntity bookEntity = bookRepository.findByTitle(id);
        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        }
    }
}
