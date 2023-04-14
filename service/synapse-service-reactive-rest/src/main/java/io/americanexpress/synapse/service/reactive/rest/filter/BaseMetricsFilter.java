package io.americanexpress.synapse.service.reactive.rest.filter;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * {@code BaseMetricsFilter} captures metrics about the request such as the response time.
 *
 * @author Elisha Aquino
 */
@Component
public abstract class BaseMetricsFilter implements WebFilter {

    /**
     * Used to log the metrics.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        return chain.filter(exchange).doFinally(signalType -> {
            long totalTime = System.currentTimeMillis() - startTime;
            ServerHttpRequest request = exchange.getRequest();
            logger.info("REQUEST_ID={}, RESPONSE_TIME={}, URI={}, STATUS={}",
                    request.getId(), totalTime, request.getPath(), exchange.getResponse().getStatusCode());
        });
    }
}
