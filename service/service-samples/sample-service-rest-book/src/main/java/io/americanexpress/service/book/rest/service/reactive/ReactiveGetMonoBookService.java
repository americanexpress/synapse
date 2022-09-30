package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveGetMonoBookService extends BaseGetMonoReactiveService<ReadBookResponse> {


    @Override
    protected Mono<ReadBookResponse> executeRead(String request) {
        logger.entry(request);
        logger.debug("emulating get mono read...");
        return Mono.just(populateSingleBookResponse(request));
    }

    private ReadBookResponse populateSingleBookResponse(String identifier) {
        ReadBookResponse response = new ReadBookResponse();
        response.setId(identifier);
        response.setTitle("title");
        response.setAuthor("author");
        return response;
    }
}
