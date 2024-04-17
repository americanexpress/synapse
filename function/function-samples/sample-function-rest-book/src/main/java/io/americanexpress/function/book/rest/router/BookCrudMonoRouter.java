package io.americanexpress.function.book.rest.router;

import io.americanexpress.function.book.rest.handler.BookCrudMonoHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCrudMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookCrudMonoRouter extends BaseCrudMonoRouter<BookCrudMonoHandler> {

    @Override
    public String getEndpoint() {
        return "/books";
    }
}
