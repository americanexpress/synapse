package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.BaseGetPolyReactiveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseReactiveGetPolyController<O extends BaseServiceResponse, S extends BaseGetPolyReactiveService<O>> extends BaseController<S> {

    @ApiOperation(value = "Reactive get flux", notes = "Gets all resources reactively")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping()
    public Flux<ResponseEntity<O>> read(HttpServletResponse httpServletResponse) {
        logger.entry();

        final var serviceResponse = service.read();
        final var responseEntity = serviceResponse
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit();
        return responseEntity;
    }
}
