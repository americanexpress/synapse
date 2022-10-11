package io.americanexpress.function.book.rest.router;

import io.americanexpress.function.book.rest.handler.BookCrudMonoHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCrudMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookCrudMonoRouter extends BaseCrudMonoRouter<BookCrudMonoHandler> {

  private String endpoint = "books";

  @Override
  protected void setEndpoint(String endpoint) {
    this.setEndpoint(endpoint);
  }
}
