package io.americanexpress.function.greeting.rest.router;

import io.americanexpress.function.greeting.rest.handler.SynapseGreetingHandler;
import io.americanexpress.synapse.function.rest.router.BaseReactiveReadMonoRouter;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SynapseGreetingRouter extends BaseReactiveReadMonoRouter<SynapseGreetingHandler> {

  @Override
  protected void setEndpoint(String endpoint) {
    this.endpoint = "hello";
  }
}
