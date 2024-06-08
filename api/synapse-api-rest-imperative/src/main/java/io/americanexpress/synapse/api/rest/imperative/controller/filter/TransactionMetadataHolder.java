package io.americanexpress.synapse.api.rest.imperative.controller.filter;


/**
 * {@code TransactionMetadataHolder} object that holds the transaction metadata during the transaction lifecycle. The
 * transaction metadata inherits the attributes and values from the Http Headers, received at the API/Protocol module.
 * The purpose is that the transaction metadata is available across all modules, without having to pass the API headers
 * across modules or objects, making the method parameters more complex.
 *
 * @author John Martinez
 */
public class TransactionMetadataHolder {

    private TransactionMetadataHolder() {}

    private static final ThreadLocal<TransactionMetadata> transactionMetadataThreadLocal = new ThreadLocal<>();

    public static void setTransactionMetadataThreadLocal(TransactionMetadata transactionMetadata) {
        transactionMetadataThreadLocal.set(transactionMetadata);
    }

    public static TransactionMetadata getTransactionMetadata() {
        return transactionMetadataThreadLocal.get();
    }

    public static void clear() {
        transactionMetadataThreadLocal.remove();
    }
}
