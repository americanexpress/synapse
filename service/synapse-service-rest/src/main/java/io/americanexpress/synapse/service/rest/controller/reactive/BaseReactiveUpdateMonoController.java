package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.service.BaseUpdateMonoReactiveService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public abstract class BaseReactiveUpdateMonoController<I extends BaseServiceRequest, S extends BaseUpdateMonoReactiveService<I>> extends BaseController<S> {

    /**
     * Update a single resource entirely.
     *
     * @param serviceRequest body from the consumer
     */
    @Operation(tags = "Update Operation", summary = "Updates a resource reactively")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> update(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        var results = service.update(serviceRequest);
        var responseEntity = results
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

        logger.exit(responseEntity);

        return responseEntity;
    }
}
