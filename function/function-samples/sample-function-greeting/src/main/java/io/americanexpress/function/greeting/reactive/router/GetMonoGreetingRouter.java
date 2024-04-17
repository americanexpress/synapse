package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.GetMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseGetMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetMonoGreetingRouter extends BaseGetMonoRouter<GetMonoGreetingHandler> {

    @Override
    public String getEndpoint() {
        return "/hello";
    }
}
