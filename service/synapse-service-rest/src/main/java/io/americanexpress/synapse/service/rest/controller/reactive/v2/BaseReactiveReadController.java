package io.americanexpress.synapse.service.rest.controller.reactive.v2;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceRequest;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.reactive.BaseReactiveReadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public abstract class BaseReactiveReadController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseReactiveReadService<I, O>> extends BaseController<S> {

    /**
     * Get a list of multiple resources from the back end service.
     *
     * @param serviceRequest body from the consumer
     * @return a list of resources from the back end service
     */
    @ApiOperation(value = "Reactive Read Poly", notes = "Gets a collection of resources", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @PostMapping("/multiple_results")
    public Flux<ResponseEntity<O>> read(@Valid @RequestBody I serviceRequest, HttpServletResponse httpServletResponse) {
        logger.entry(serviceRequest);

        Flux<O> serviceResponse = service.read(serviceRequest);

        Flux<ResponseEntity<O>> responseEntity = serviceResponse.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit(responseEntity);
        return responseEntity;
    }
}
