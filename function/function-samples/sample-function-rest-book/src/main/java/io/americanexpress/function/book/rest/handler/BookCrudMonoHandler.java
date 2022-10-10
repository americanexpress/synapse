package io.americanexpress.function.book.rest.handler;

import io.americanexpress.function.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.function.reactive.handler.BaseCrudMonoHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BookCrudMonoHandler extends BaseCrudMonoHandler<ReadBookResponse> {

  @Override
  protected Mono<ServerResponse> executeGetAll(ServerRequest request) {

    logger.info("executeGetAll");
    return request.checkNotModified("tset");
  }

  @Override
  protected Mono<ServerResponse> executeCreate(ReadBookResponse object) {
    return null;
  }

  @Override
  protected Mono<ServerResponse> executeGetById(String id) {
    return null;
  }

  @Override
  protected Mono<ServerResponse> executeUpdateById(String id, ReadBookResponse object) {
    return null;
  }

  @Override
  protected Mono<ServerResponse> executeDeleteById(String id) {
    return null;
  }
}
