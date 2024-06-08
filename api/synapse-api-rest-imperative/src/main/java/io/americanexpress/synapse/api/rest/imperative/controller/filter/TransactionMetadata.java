package io.americanexpress.synapse.api.rest.imperative.controller.filter;

/**
 * {@code TransactionMetadata} object contains all the HTTP request header values, carried across a single transaction.
 * This metadata can be accessed across all modules, by the invocation of {@link TransactionMetadataHolder} bean.
 * <p>
 *
 * This class can be extended to make a custom implementation.
 *
 * @author John Martinez
 */
public class TransactionMetadata {

    /**
     * Fields that directly inherits all the values from the HTTP Headers. They are copied during the ..... TODO
     */
    private Common common;

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }
}
