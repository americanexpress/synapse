package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import reactor.core.publisher.Flux;

public abstract class BaseGetPolyReactiveService<O extends BaseServiceResponse> extends BaseService {

    public Flux<O> read() {
        logger.entry();
        var response = executeRead();
        logger.exit();
        return response;
    }

    protected abstract Flux<O> executeRead();
}
