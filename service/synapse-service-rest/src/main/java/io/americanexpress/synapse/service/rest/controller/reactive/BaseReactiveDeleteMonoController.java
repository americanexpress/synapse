package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.service.BaseDeleteMonoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

public abstract class BaseReactiveDeleteMonoController<S extends BaseDeleteMonoService> extends BaseController<S> {

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
        var results = service.delete(identifier);
        logger.exit();
        return results;
    }
}
