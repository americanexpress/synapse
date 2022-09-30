package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.synapse.service.rest.service.BaseCreateReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCreateBookService extends BaseCreateReactiveService<ReadPolyBookRequest, ReadBookResponse> {
    @Override
    protected Mono<ReadBookResponse> executeCreate(ReadPolyBookRequest request) {
        logger.entry(request);
        logger.debug("emulating create...");
        return Mono.just(createReadBookResponse(request));
    }

    private ReadBookResponse createReadBookResponse(ReadPolyBookRequest request) {
        return new ReadBookResponse(request.getIdentifier(), request.getTitle(), request.getAuthor());
    }
}
