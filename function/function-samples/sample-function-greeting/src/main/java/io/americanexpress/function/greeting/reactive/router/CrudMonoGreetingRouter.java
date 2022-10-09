package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.CrudMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCrudMonoRouter;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudMonoGreetingRouter extends BaseCrudMonoRouter<CrudMonoGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseCrudMonoRouter.endpoint = "/hello";
    }
}
