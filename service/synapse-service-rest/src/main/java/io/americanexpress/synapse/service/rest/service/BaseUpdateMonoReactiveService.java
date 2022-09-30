package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import reactor.core.publisher.Mono;

/**
 * {@code BaseUpdateMonoService}
 * @param <I> Input request type
 */
public abstract class BaseUpdateMonoReactiveService<I extends BaseServiceRequest> extends BaseService {

    /**
     * Update a single resource reactively.
     * @param request body received from the controller
     * @return
     */
    public Mono<Void> update(I request) {
        logger.entry(request);
        var results = executeUpdate(request);
        logger.exit();
        return results;
    }

    /**
     * Prototype for updating a resource.
     * @param request
     * @return
     */
    protected abstract Mono<Void> executeUpdate(I request);
}
