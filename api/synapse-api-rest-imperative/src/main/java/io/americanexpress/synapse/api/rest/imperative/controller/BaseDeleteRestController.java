package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.service.imperative.model.BaseDeleteServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.service.BaseDeleteImperativeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code BaseDeleteRestController} class specifies the prototypes for listening for requests from the consumer
 * to Delete (DELETE) a resource form an id. This controller expects only one entry per request.
 *
 * @param <S> service type
 * @author Luis Diaz
 */
public class BaseDeleteRestController<
            I extends BaseDeleteServiceRequest,
            S extends BaseDeleteImperativeService<I>
        > extends BaseController<S> {

    /**
     * Delete a single resource.
     *
     * @param headers containing the HTTP headers from the consumer
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Delete Operation", summary = "Deletes a resource from an ID")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable("id") String id) {
        I serviceRequest = (I) new BaseDeleteServiceRequest();
        serviceRequest.setId(id);
        logger.entry(serviceRequest);
        service.delete(serviceRequest);
        logger.exit();
    }
}
