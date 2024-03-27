package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.mysql.book.dao.BookRepository;
import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookServiceMapper;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import io.americanexpress.synapse.service.reactive.rest.service.BaseGetFluxReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code GetAllBookService} Gets all book resources.
 */
@Service
public class GetAllBookService extends BaseGetFluxReactiveService<ReadBookResponse> {

    /**
     * The bookRepository to access the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor taking in and autowiring BookRepository
     *
     * @param bookRepository used to query the database.
     */
    public GetAllBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * ExecuteRead will be used to get all book resources.
     *
     * @param headers http server headers.
     * @return All book objects from the database.
     */
    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers) {
        return bookRepository.findAll()
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Not found.", ErrorCode.NOT_FOUND, (String[]) null)));
    }
}
