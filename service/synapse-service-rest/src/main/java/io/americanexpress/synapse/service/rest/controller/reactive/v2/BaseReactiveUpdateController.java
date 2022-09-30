package io.americanexpress.synapse.service.rest.controller.reactive.v2;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public abstract class BaseReactiveUpdateController<I extends BaseServiceRequest, S extends BaseReactiveUpdateService<I>> extends BaseController<S> {

    /**
     * Update a single resource entirely.
     *
     * @param serviceRequest body from the consumer
     * @return
     */
    @Operation(tags = "Update Operation", summary = "Updates a resource reactively")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        Mono<Void> results = service.update(serviceRequest);

        logger.exit(results);

        return results;
    }

}
