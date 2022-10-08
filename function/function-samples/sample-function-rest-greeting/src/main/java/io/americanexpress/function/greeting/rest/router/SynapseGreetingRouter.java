package io.americanexpress.function.greeting.rest.router;

import io.americanexpress.function.greeting.rest.handler.SynapseGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SynapseGreetingRouter extends BaseReadMonoRouter<SynapseGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseReadMonoRouter.endpoint = "/hello";
    }
}
