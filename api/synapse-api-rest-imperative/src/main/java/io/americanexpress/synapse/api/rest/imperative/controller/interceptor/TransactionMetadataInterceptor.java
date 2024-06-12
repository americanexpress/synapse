package io.americanexpress.synapse.api.rest.imperative.controller.interceptor;

import io.americanexpress.synapse.api.rest.imperative.controller.interceptor.helper.TransactionMetadataCreator;
import io.americanexpress.synapse.api.rest.imperative.controller.interceptor.helper.TransactionMetadataHolder;
import io.americanexpress.synapse.api.rest.imperative.controller.interceptor.model.TransactionMetadata;
import io.americanexpress.synapse.framework.exception.ApplicationClientException;
import io.americanexpress.synapse.framework.exception.model.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TransactionMetadataInterceptor implements HandlerInterceptor {

    private final TransactionMetadataCreator transactionMetadataCreator;

    public TransactionMetadataInterceptor(TransactionMetadataCreator transactionMetadataCreator) {
        this.transactionMetadataCreator = transactionMetadataCreator;
    }

    /**
     * Handles the creation of the {@link TransactionMetadata} that get stored in the {@link TransactionMetadataHolder}
     * at the thread level, to later be accessed as a Bean.
     *
     * @param request  raw http request
     * @param response raw http response
     * @param handler  object
     * @return boolean TRUE, if the execution of the metadata creation is created successfully. FALSE, if the object is
     * not created as expected.
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        TransactionMetadata metadata = transactionMetadataCreator.create(request);
        if (metadata == null) {
            throw new ApplicationClientException("The TransactionMetadata object could not be created successfully", ErrorCode.GENERIC_5XX_ERROR);
        }
        TransactionMetadataHolder.setTransactionMetadataThreadLocal(metadata);
        return true;
    }

    /**
     * Handles the clean-up of the transaction metadata after the execution of the transaction have been finalized.
     *
     * @param request  raw http request
     * @param response raw http response
     * @param handler  object
     * @param ex       exception object.
     */
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        TransactionMetadataHolder.clear();
    }
}
