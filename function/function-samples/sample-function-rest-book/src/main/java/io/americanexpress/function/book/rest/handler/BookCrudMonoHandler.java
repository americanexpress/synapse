package io.americanexpress.function.book.rest.handler;

import io.americanexpress.function.book.rest.model.ReadBookResponse;
import io.americanexpress.synapse.function.reactive.handler.BaseCrudMonoHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * {@code BookCrudMonoHandler} the book crud mono handler.
 */
@Component
public class BookCrudMonoHandler extends BaseCrudMonoHandler<ReadBookResponse> {

  /**
   * execute the get all book mono handler.
   * @param request the request
   * @return a mono server response
   */
  @Override
  protected Mono<ServerResponse> executeGetAll(ServerRequest request) {

    logger.info("executeGetAll");
    return request.checkNotModified("book");
  }

  /**
   * Executes the create book mono handler.
   * @param object the read book response object
   * @return a mono server response
   */
  @Override
  protected Mono<ServerResponse> executeCreate(ReadBookResponse object) {
    return null;
  }

  /**
   * Executes the get by id book mono handler.
   * @param id the identifier
   * @return  a mono server response
   */
  @Override
  protected Mono<ServerResponse> executeGetById(String id) {
    return null;
  }

  /**
   * Executes the update book mono handler.
   * @param id the identifier
   * @return  a mono server response
   */
  @Override
  protected Mono<ServerResponse> executeUpdateById(String id, ReadBookResponse object) {
    return null;
  }

  /**
   * Executes the delete by id book mono handler.
   * @param id the identifier
   * @return  a mono server response
   */
  @Override
  protected Mono<ServerResponse> executeDeleteById(String id) {
    return null;
  }
}
