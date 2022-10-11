package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookService extends BaseDeleteService {

    private final BookRepository bookRepository;

    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected void executeDelete(String title) {
        bookRepository.deleteByTitle(title);
    }
}
