package io.americanexpress.synapse.service.reactive.rest.filter;

import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code BaseHttpHeadersFilter} performs HTTP headers validation.
 *
 * @author Wendy Hu
 */
@Component
public abstract class BaseHttpHeadersFilter implements WebFilter {

    /**
     * Used for logging.
     */
    private final XLogger logger = XLoggerFactory.getXLogger(getClass());

    /**
     * Intercepts the request to check the headers.
     *
     * @param exchange the server web exchange
     * @param chain the web filter chain
     * @return a void mono
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        logger.entry(exchange.getRequest(), exchange.getResponse());

        if(!shouldNotFilter(exchange.getRequest())) {
            // If any of the required HTTP headers are missing, this request is invalid
            String httpHeaderValue;
            for (String requiredHttpHeaderName : getRequiredHttpHeaderNames()) {
                httpHeaderValue = exchange.getRequest().getHeaders().getFirst(requiredHttpHeaderName);
                if (httpHeaderValue == null) {
                    throw new ApplicationClientException("Request HTTP Header " + requiredHttpHeaderName + " is missing.",
                            ErrorCode.MISSING_HTTP_HEADER_ERROR, requiredHttpHeaderName);
                }
            }
        }

        // HTTP header validation is successful
        logger.exit();
        return chain.filter(exchange);
    }

    /**
     * Get the required HTTP header names to be validated.
     *
     * @return the required HTTP header names
     */
    protected List<String> getRequiredHttpHeaderNames() {
        // Note: it is possible that a service needs no request HTTP header validation
        // Should a service require request HTTP header validation, then override this method
        return new ArrayList<>();
    }

    /**
     * Subclasses can override this method to control what requests should not be filtered.
     * for example override the method with the following to prevent
     * health endpoint : return request.getURI().getPath().equals("/health")
     *
     * @param request the incoming request
     * @return true if url should not be filtered
     */
    protected boolean shouldNotFilter(ServerHttpRequest request) {
        return false;
    }
}
