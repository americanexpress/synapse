package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
/**
 * {@code BaseUpdateService} class specifies the prototypes for performing business logic.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @author Francois Gutt
 */
public class BaseUpdateImperativeRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseService<I, O>
        > extends BaseController<S> {

    /**
     * Update a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @Operation(tags = "Update Operation", summary = "Updates a resource")
    public ResponseEntity<O> update(@RequestHeader HttpHeaders headers, @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        O serviceResponse = service.execute(serviceRequest);
        ResponseEntity<O> responseEntity = CreateResponseEntityCreator.create(serviceResponse);
        logger.exit();
        return responseEntity;
    }
}
