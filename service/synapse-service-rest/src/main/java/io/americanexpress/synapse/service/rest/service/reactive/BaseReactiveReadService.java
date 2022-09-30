package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Flux;

public abstract class BaseReactiveReadService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    public Flux<O> read(final I request) {

        logger.entry(request);

        final Flux<O> responses = executeRead(request);

        logger.exit(responses);

        return responses;
    }

    protected abstract Flux<O> executeRead(I request);

}
