package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.MonoResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseGetMonoImperativeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.validation.Valid;

/**
 * {@code BaseGetMonoImperativeRestController} is base class for read mono controller. This controller handles POST method requests,
 * but specifically for read purposes.
 * This controller returns a single object.
 *
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseGetMonoImperativeService}
 * @author Francois Gutt
 */
public class BaseGetMonoImperativeRestController<
            O extends BaseServiceResponse,
            S extends BaseGetMonoImperativeService<O>
        > extends BaseController<S> {

    /**
     * Read response entity.
     * @param headers containing the HTTP headers from the consumer
     * @return the response entity
     */
    @Operation(summary = "Read operation based on path.", description = "Read one resource based on a path variable.")
    @GetMapping
    public ResponseEntity<O> read(@RequestHeader HttpHeaders headers) {
        logger.entry(headers);
        final O response = service.read(headers);
        ResponseEntity<O> responseEntity = MonoResponseEntityCreator.create(response);
        logger.exit(responseEntity);
        return responseEntity;
    }
}
