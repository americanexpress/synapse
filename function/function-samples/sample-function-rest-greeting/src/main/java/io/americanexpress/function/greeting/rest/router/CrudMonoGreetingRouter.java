package io.americanexpress.function.greeting.rest.router;

import io.americanexpress.function.greeting.rest.handler.CrudMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCrudMonoRouter;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudMonoGreetingRouter extends BaseCrudMonoRouter<CrudMonoGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseReadMonoRouter.endpoint = "/hello";
    }
}
