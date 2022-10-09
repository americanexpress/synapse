package io.americanexpress.function.greeting.rest.router;

import io.americanexpress.function.greeting.rest.handler.CreateMonoGreetingHandler;
import io.americanexpress.function.greeting.rest.handler.ReadMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCreateMonoRouter;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMonoGreetingRouter extends BaseCreateMonoRouter<CreateMonoGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseReadMonoRouter.endpoint = "/hello";
    }
}
