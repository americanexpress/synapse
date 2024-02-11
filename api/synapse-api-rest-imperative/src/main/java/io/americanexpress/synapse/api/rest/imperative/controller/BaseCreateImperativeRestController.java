package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.CreateResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseCreateImperativeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;

/**
 * {@code BaseCreateService} class specifies the prototypes for performing business logic.
 *
 * @param <I> input request type
 * @param <O> output response type
 * @author Francois Gutt
 */
public class BaseCreateImperativeRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseCreateImperativeService<I, O>
        > extends BaseController<S> {

    /**
     * Create a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @Operation(tags = "Create Operation", summary = "Creates a resource")
    public ResponseEntity<O> create(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        O serviceResponse = service.create(serviceRequest);
        ResponseEntity<O> responseEntity = CreateResponseEntityCreator.create(serviceResponse);
        logger.exit();
        return responseEntity;
    }
}
