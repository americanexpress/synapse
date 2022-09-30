package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.synapse.service.rest.service.BaseReadMonoReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveReadMonoBookService extends BaseReadMonoReactiveService<ReadPolyBookRequest, ReadBookResponse> {

    @Override
    protected Mono<ReadBookResponse> executeRead(ReadPolyBookRequest request) {
        logger.entry(request);
        logger.debug("emulating post read...");
        return Mono.just(new ReadBookResponse("1", "title", "title"));
    }
}
