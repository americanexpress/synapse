package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.ReadMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadMonoGreetingRouter extends BaseReadMonoRouter<ReadMonoGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseReadMonoRouter.endpoint = "/hello";
    }
}
