package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.service.book.rest.model.ReadPolyBookRequest;
import io.americanexpress.synapse.service.rest.service.BaseUpdateReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUpdateBookService extends BaseUpdateReactiveService<ReadPolyBookRequest> {
    @Override
    protected Mono<Void> executeUpdate(ReadPolyBookRequest request) {
        logger.entry(request);
        logger.debug("emulating update...");
        return Mono.empty();
    }
}
