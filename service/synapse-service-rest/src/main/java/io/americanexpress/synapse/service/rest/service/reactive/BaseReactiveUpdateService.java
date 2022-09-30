package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Mono;

/**
 * {@code BaseUpdateMonoService}
 * @param <I> Input request type
 */
public abstract class BaseReactiveUpdateService<I extends BaseServiceRequest> extends BaseService {

    /**
     * Update a single resource reactively.
     * @param request body received from the controller
     */
    public Mono<Void> update(I request) {
        logger.entry(request);
        Mono<Void> results = executeUpdate(request);
        logger.exit();
        return results;
    }

    /**
     * Prototype for updating a resource.
     * @param request
     */
    protected abstract Mono<Void> executeUpdate(I request);

}
