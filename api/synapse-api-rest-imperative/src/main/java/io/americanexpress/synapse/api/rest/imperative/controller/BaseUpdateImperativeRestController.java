package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.CreateResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * {@code BaseUpdateImperativeRestController} class specifies the prototypes for performing business logic.
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
    @PutMapping
    public ResponseEntity<O> update(@RequestHeader HttpHeaders headers, I serviceRequest) {
        logger.entry(serviceRequest);
        O serviceResponse = service.execute(serviceRequest);
        ResponseEntity<O> responseEntity = CreateResponseEntityCreator.create(serviceResponse);
        logger.exit();
        return responseEntity;
    }
}
