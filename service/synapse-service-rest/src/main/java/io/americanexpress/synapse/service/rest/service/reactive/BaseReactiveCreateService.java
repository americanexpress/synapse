package io.americanexpress.synapse.service.rest.service.reactive;

import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseService;
import reactor.core.publisher.Mono;

public abstract class BaseReactiveCreateService<I extends BaseServiceRequest, O extends BaseServiceResponse> extends BaseService {

    /**
     * Add a single resource.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    public Mono<O> create(I request) {
        logger.entry(request);
        final Mono<O> response = executeCreate(request);
        logger.exit(response);
        return response;
    }

    /**
     * Prototype for adding a resource.
     *
     * @param request body received from the controller
     * @return response body to the controller
     */
    protected abstract Mono<O> executeCreate(I request);
}
