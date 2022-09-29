package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import reactor.core.publisher.Mono;

public abstract class BaseDeleteMonoService extends BaseService {

    public Mono<Void> delete(String identifier) {
        logger.entry();
        var results = executeDelete(identifier);
        logger.exit();
        return results;
    }

    protected abstract Mono<Void> executeDelete(String identifier);
}
