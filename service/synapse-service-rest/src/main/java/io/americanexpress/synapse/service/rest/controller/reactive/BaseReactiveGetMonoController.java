package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.controller.reactive.helpers.ReactiveMonoResponseEntityCreator;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoReactiveService;
import io.americanexpress.synapse.service.rest.service.BaseGetMonoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public abstract class BaseReactiveGetMonoController<O extends BaseServiceResponse, S extends BaseGetMonoReactiveService<O>> extends BaseController<S> {

    private static final String GET_RESULTS = "/{id}";

    private final ReactiveMonoResponseEntityCreator<O> reactiveMonoResponseEntityCreator;

    @Autowired
    public BaseReactiveGetMonoController(ReactiveMonoResponseEntityCreator<O> reactiveMonoResponseEntityCreator) {
        this.reactiveMonoResponseEntityCreator = reactiveMonoResponseEntityCreator;
    }

    @ApiOperation(value = "Reactive get mono", notes = "Gets one resource reactively")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping(GET_RESULTS)
    public Mono<ResponseEntity<O>> read(@PathVariable String id) {
        logger.entry(id);

        var serviceResponse = service.read(id);
        var responseEntity = serviceResponse
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit(responseEntity);
        return responseEntity;
    }
}
