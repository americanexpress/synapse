package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.api.rest.imperative.controller.helpers.MonoResponseEntityCreator;
import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.service.BaseReadMonoImperativeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;

/**
 * {@code BaseReadMonoController} class specifies the prototypes for listening for requests from the consumer
 * to Read (POST) a resource. This Controller expects only one object in request and one object in the
 * response, hence, "Mono" in the name.
 *
 * @param <I> an object extending the {@link BaseServiceRequest}
 * @param <O> an object extending the {@link BaseServiceResponse}
 * @param <S> an object extending the {@link BaseReadMonoImperativeService}
 * @author Gabriel Jimenez
 */
public class BaseReadMonoImperativeRestController<
            I extends BaseServiceRequest,
            O extends BaseServiceResponse,
            S extends BaseReadMonoImperativeService<I, O>
        > extends BaseController<S> {

    /**
     * Constant string for inquiry_results.
     */
    public static final String INQUIRY_RESULTS = "/inquiry-results";

    /**
     * Get a single resource from the back end service.
     *
     * @param headers containing the HTTP headers from the consumer
     * @param serviceRequest body from the consumer
     * @return a single resource from the back end service
     */
    @Operation(tags = "Read Mono Operation", summary = "Read Mono Operation", description = "Reads one resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping(INQUIRY_RESULTS)
    public ResponseEntity<O> read(@RequestHeader HttpHeaders headers, @Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        final O serviceResponse = service.read(serviceRequest);
        ResponseEntity<O> responseEntity = MonoResponseEntityCreator.create(serviceResponse);

        logger.exit(responseEntity);
        return responseEntity;
    }
}
