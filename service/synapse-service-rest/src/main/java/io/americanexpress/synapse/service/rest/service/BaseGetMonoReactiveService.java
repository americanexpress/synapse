package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import reactor.core.publisher.Mono;

public abstract class BaseGetMonoReactiveService<O extends BaseServiceResponse> extends BaseService {

    public Mono<O> read(String identifier) {
        logger.entry(identifier);
        final var response = executeRead(identifier);
        logger.exit();
        return response;
    }

    protected abstract Mono<O> executeRead(String request);
}
