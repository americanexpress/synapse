package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.controller.reactive.helpers.ReactivePolyResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetPolyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseReactiveGetPolyController<O extends BaseServiceResponse, S extends BaseGetPolyService<O>> extends BaseController<S> {

    private final ReactivePolyResponseEntityCreator<O> reactivePolyResponseEntityCreator;

    @Autowired
    public BaseReactiveGetPolyController(ReactivePolyResponseEntityCreator<O> reactivePolyResponseEntityCreator) {
        this.reactivePolyResponseEntityCreator = reactivePolyResponseEntityCreator;
    }

    @ApiOperation(value = "Reactive get flux", notes = "Gets all resources reactively")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping()//TODO: What would be the mapping to retrieve the entire resource?
    public ResponseEntity<Flux<O>> read(HttpServletResponse httpServletResponse) {
        logger.entry();
        Page<O> serviceResponse = service.read();
        ResponseEntity<Flux<O>> responseEntity = reactivePolyResponseEntityCreator.create(serviceResponse, httpServletResponse);

        logger.exit();
        return responseEntity;
    }
}
