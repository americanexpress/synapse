package io.americanexpress.synapse.service.rest.controller.reactive.v2;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveCreateService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public abstract class BaseReactiveCreateController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReactiveCreateService<I, O>> extends BaseController<S> {

    /**
     * Create a single resource.
     *
     * @param serviceRequest body from the consumer
     * @return response to the consumer
     */
    @PostMapping
    @Operation(tags = "Reactive Create Operation", summary = "Creates a reactive resource")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    public Mono<ResponseEntity<O>> create(@Valid @RequestBody I serviceRequest) {
        logger.entry(serviceRequest);

        final Mono<O> serviceResponse = service.create(serviceRequest);
        Mono<ResponseEntity<O>> responseEntity = serviceResponse.map(ResponseEntity.status(HttpStatus.CREATED)::body)
                        .defaultIfEmpty(ResponseEntity.internalServerError().build());

        logger.exit();
        return responseEntity;
    }
}
