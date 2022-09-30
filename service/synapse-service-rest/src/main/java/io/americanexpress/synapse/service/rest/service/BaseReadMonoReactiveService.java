package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import reactor.core.publisher.Mono;

public abstract class BaseReadMonoReactiveService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    public Mono<O> read(I request) {
        logger.entry(request);
        final var response = executeRead(request);
        logger.exit();
        return response;
    }

    protected abstract Mono<O> executeRead(I request);
}
