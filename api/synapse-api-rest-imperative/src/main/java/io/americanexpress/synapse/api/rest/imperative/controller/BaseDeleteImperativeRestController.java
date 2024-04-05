package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code BaseDeleteController} class specifies the prototypes for listening for requests from the consumer
 * to Delete (DELETE) a resource. This controller expects only one entry per request.
 *
 * @param <S> service type
 * @author Gabriel Jimenez
 */
public class BaseDeleteImperativeRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseService<I, O>
        > extends BaseController<S> {

    /**
     * Delete a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Delete Operation", summary = "Deletes a resource")
    public void delete(@RequestHeader HttpHeaders headers, @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);
        service.execute(serviceRequest);
        logger.exit();
    }
}
