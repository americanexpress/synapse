package io.americanexpress.function.greeting.reactive.router;

import io.americanexpress.function.greeting.reactive.handler.CreateMonoGreetingHandler;
import io.americanexpress.synapse.function.reactive.router.BaseCreateMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateMonoGreetingRouter extends BaseCreateMonoRouter<CreateMonoGreetingHandler> {

    @Override
    public String getEndpoint() {
        return "/hello";
    }
}
