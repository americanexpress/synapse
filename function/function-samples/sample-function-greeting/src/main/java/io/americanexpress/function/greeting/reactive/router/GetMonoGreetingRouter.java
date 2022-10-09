package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.GetMonoGreetingHandler;
import io.americanexpress.function.greeting.reactive.handler.ReadMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseGetMonoRouter;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMonoGreetingRouter extends BaseGetMonoRouter<GetMonoGreetingHandler> {

    @Override
    protected void setEndpoint(String endpoint) {
        BaseGetMonoRouter.endpoint = "/hello";
    }
}
