package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import reactor.core.publisher.Flux;

public abstract class BaseReadPolyReactiveService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    public Flux<O> read(final I request) {
        logger.entry(request);

        final var response  = executeRead(request);

        logger.exit(response);

        return response;
    }

    protected abstract Flux<O> executeRead(I request);
}
