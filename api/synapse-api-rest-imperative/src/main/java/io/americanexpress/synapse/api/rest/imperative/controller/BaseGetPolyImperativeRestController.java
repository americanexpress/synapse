package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.PolyResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.model.PageResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

/**
 * {@code BaseGetPolyImperativeRestController} class specifies the prototypes for listening for requests from the consumer
 * to Read (GET) a resource. This Controller expects no request and a list of objects as response, hence, "Poly" in the name.
 *
 * @param <I> an object extending {@link BaseServiceRequest}
 * @param <O> an object extending {@link BaseServiceResponse}
 * @param <S> an object extending {@link BaseService}
 * @author Tanvir Islam
 */
public class BaseGetPolyImperativeRestController<
        I extends BaseServiceRequest,
        O extends BaseServiceResponse,
        S extends BaseService<I, PageResponse<O>>
        > extends BaseController<S> {

    /**
     * Get a list of multiple resources from the back end service.
     *
     * @param httpHeaders           containing the HTTP headers from the consumer
     * @param serviceRequest        body from the consumer
     * @return a list of resources from the back end service
     */
    @Operation(summary = "Read operation based on path.", description = "Read a collection of resources based on request criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping
    public ResponseEntity<List<O>> read(@RequestHeader HttpHeaders httpHeaders, I serviceRequest) {
        logger.entry(serviceRequest);

        final PageResponse<O> page = service.execute(serviceRequest);
        final ResponseEntity<List<O>> responseEntity = PolyResponseEntityCreator.create(page);

        logger.exit(responseEntity);
        return responseEntity;
    }
}
