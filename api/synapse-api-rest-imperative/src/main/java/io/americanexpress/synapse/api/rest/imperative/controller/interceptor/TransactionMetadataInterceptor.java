package io.americanexpress.synapse.api.rest.imperative.controller.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TransactionMetadataInterceptor implements HandlerInterceptor {

    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        

    }


}
