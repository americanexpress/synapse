package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.UpdateMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseUpdateMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateMonoGreetingRouter extends BaseUpdateMonoRouter<UpdateMonoGreetingHandler> {

    @Override
    public String getEndpoint() {
        return "/hello";
    }
}
