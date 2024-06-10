package io.americanexpress.synapse.api.rest.imperative.controller.interceptor.model;

import io.americanexpress.synapse.api.rest.imperative.controller.interceptor.helper.TransactionMetadataHolder;

/**
 * {@code TransactionMetadata} object contains all the HTTP request header values, carried across a single transaction.
 * This metadata can be accessed across all modules, by the invocation of {@link TransactionMetadataHolder} bean.
 * <p>
 * <p>
 * This class can be extended to make a custom implementation.
 *
 * @author John Martinez
 */
public class TransactionMetadata {

    /**
     * Fields that directly inherits all the values from the HTTP Headers. They are copied during the ..... TODO
     */
    private CommonMetadata commonMetadata;

    public CommonMetadata getCommon() {
        return commonMetadata;
    }

    public void setCommon(CommonMetadata commonMetadata) {
        this.commonMetadata = commonMetadata;
    }
}
