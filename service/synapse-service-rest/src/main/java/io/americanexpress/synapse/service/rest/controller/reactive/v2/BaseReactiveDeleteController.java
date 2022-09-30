package io.americanexpress.synapse.service.rest.controller.reactive.v2;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

public abstract class BaseReactiveDeleteController<S extends BaseReactiveDeleteService> extends BaseController<S> {

    /**
     * Delete a single resource.
     *
     * @param identifier of the resource to be deleted
     */
    @DeleteMapping("/{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Delete Operation", summary = "Deletes a resource reactively")
    public Mono<Void> delete(@PathVariable String identifier) {
        logger.entry(identifier);

        var serviceResults = service.delete(identifier);

        logger.exit(serviceResults);

        return serviceResults;
    }
}
