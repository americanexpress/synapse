package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.cp.book.service.BookPersistenceService;
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
     * bookPersistenceService
     */
    private BookPersistenceService bookPersistenceService;

    /**
     * Constructor of {@Ccode GetAllBookService} with provided {@link BookPersistenceService}.
     *
     * @param bookPersistenceService The {@link BookPersistenceService} used to perform CRUD operations asynchronously
     *                               through a connection pool.
     */
    public GetAllBookService(BookPersistenceService bookPersistenceService) {
        this.bookPersistenceService = bookPersistenceService;
    }

    /**
     * executeRead will be used to get all book resources.
     *
     * @param headers The {@link HttpHeaders} of the request.
     * @return A {@link Flux} emitting all {@link ReadBookResponse} records found in the database.
     */
    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers) {
        return bookPersistenceService.executeFindAll()
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)));
    }
}
