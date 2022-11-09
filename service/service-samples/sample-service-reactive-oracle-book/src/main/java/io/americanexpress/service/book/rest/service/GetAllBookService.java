package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetPolyReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * {@code GetAllBookService} Gets all book resources.
 */
@Service
public class GetAllBookService extends BaseGetPolyReactiveService<ReadBookResponse> {

    /**
     * bookRepository
     */
    private BookRepository bookRepository;

    public GetAllBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * executeRead will be used to get all book resources.
     * @return
     */
    @Override
    protected Flux<ReadBookResponse> executeRead() {
        return bookRepository.findAll()
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Flux.empty());
    }
}
