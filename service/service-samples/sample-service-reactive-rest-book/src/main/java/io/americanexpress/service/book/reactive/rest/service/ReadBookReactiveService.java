package io.americanexpress.service.book.reactive.rest.service;

import io.americanexpress.service.book.reactive.rest.model.ReadBookRequest;
import io.americanexpress.service.book.reactive.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.reactive.rest.service.BaseReadMonoReactiveService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code ReadMonoBookReactiveService} service layer for retrieving a book resources
 */
@Service
public class ReadBookReactiveService extends BaseReadMonoReactiveService<ReadBookRequest, ReadBookResponse> {

    /**
     * Overriding executeRead.
     * @param request a read mono book service request
     * @return a mono read book response
     */
    @Override
    protected Mono<ReadBookResponse> executeRead(HttpHeaders headers, ReadBookRequest request) {
        logger.entry(request);
        logger.debug("emulating post read...");
        return Mono.just(new ReadBookResponse("1", "title", "title"));
    }
}
