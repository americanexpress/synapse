package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.oracle.book.dao.BookSPRepository;
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
     * bookRepository
     */
    private BookSPRepository bookSPRepository;

    public GetAllBookService(BookSPRepository bookSPRepository) {
        this.bookSPRepository = bookSPRepository;
    }


    /**
     * executeRead will be used to get all book resources.
     * @return
     */
    @Override
    protected Flux<ReadBookResponse> executeRead(HttpHeaders headers) {
        return bookSPRepository.getBookData()
                .map(BookServiceMapper::populateReadBookResponse)
                .switchIfEmpty(Mono.error(new ApplicationClientException("Bad request", ErrorCode.GENERIC_4XX_ERROR, (String[]) null)));
    }
}
