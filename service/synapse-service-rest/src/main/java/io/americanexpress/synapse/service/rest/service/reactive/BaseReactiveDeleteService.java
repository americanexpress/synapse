package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Mono;

public abstract class BaseReactiveDeleteService extends BaseService {

    public Mono<Void> delete(String identifier) {
        logger.entry();
        var results = executeDelete(identifier);
        logger.exit();
        return results;
    }

    protected abstract Mono<Void> executeDelete(String identifier);

}
