package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.DeleteMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseDeleteMonoRouter;
import io.americanexpress.synapse.function.reactive.router.BaseReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteMonoGreetingRouter extends BaseDeleteMonoRouter<DeleteMonoGreetingHandler> {

    @Override
    public String getEndpoint() {
        return "/hello";
    }
}
