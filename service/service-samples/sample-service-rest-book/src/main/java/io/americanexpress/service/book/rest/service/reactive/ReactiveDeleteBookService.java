package io.americanexpress.service.book.rest.service.reactive;

import io.americanexpress.synapse.service.rest.service.BaseDeleteReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveDeleteBookService extends BaseDeleteReactiveService {
    @Override
    protected Mono<Void> executeDelete(String identifier) {
        logger.entry(identifier);
        logger.debug("emulating delete...");
        return Mono.empty();
    }
}
