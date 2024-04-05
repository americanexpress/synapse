package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.PolyResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.model.PageResponse;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * {@code BaseReadPolyController} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and a list of objects as response, hence, "Poly" in the name.
 *
 * @param <I> an object extending {@link BaseServiceRequest}
 * @param <O> an object extending {@link BaseServiceResponse}
 * @param <S> an object extending {@link BaseService}
 * @author Gabriel Jimenez
 */
public class BaseReadPolyImperativeRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseService<I, PageResponse<O>>
        > extends BaseController<S> {

    /**
     * Constant string used for multiple_results.
     */
    public static final String MULTIPLE_RESULTS = "/multiple-results";

    /**
     * Get a list of multiple resources from the back end service.
     *
     * @param headers               containing the HTTP headers from the consumer
     * @param serviceRequest        body from the consumer
     * @return a list of resources from the back end service
     */
    @Operation(summary = "Read operation based on criteria.", description = "Read a collection of resources based on request criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping(MULTIPLE_RESULTS)
    public ResponseEntity<List<O>> read(@RequestHeader HttpHeaders headers, @RequestBody I serviceRequest, HttpServletResponse httpServletResponse) {
        logger.entry(serviceRequest);

        final PageResponse<O> page = service.execute(serviceRequest);
        final ResponseEntity<List<O>> responseEntity = PolyResponseEntityCreator.create(page);

        logger.exit(responseEntity);
        return responseEntity;
    }
}
