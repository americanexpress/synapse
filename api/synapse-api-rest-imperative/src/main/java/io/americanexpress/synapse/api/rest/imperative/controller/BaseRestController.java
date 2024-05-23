package io.americanexpress.synapse.api.rest.imperative.controller;

import io.americanexpress.synapse.service.imperative.model.BaseServiceRequest;
import io.americanexpress.synapse.service.imperative.model.BaseServiceResponse;
import io.americanexpress.synapse.service.imperative.model.TransactionMetadata;
import io.americanexpress.synapse.service.imperative.service.BaseService;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseRestController<I extends BaseServiceRequest, O extends BaseServiceResponse, S extends BaseService<I, O>> extends BaseController<S> {


    // Extract the header from the controller and loop through all the possible fields if available in the header.
    void createTransactionMetadata(HttpHeaders headers, I request) {
        TransactionMetadata transactionMetadata = new TransactionMetadata();
        transactionMetadata.setAccept(Optional.ofNullable(headers.get(HttpHeaders.ACCEPT)).orElse(Collections.emptyList()));
        transactionMetadata.setAcceptLanguage(Optional.ofNullable(headers.get(HttpHeaders.ACCEPT_LANGUAGE)).orElse(Collections.emptyList()));
        transactionMetadata.setAuthorization(Optional.ofNullable(headers.get(HttpHeaders.AUTHORIZATION)).orElse(Collections.emptyList()));
        transactionMetadata.setCacheControl(Optional.ofNullable(headers.get(HttpHeaders.CACHE_CONTROL)).orElse(Collections.emptyList()));
        transactionMetadata.setContentEncoding(Optional.ofNullable(headers.get(HttpHeaders.CONTENT_ENCODING)).orElse(Collections.emptyList()));
        transactionMetadata.setContentLanguage(Optional.ofNullable(headers.get(HttpHeaders.CONTENT_LANGUAGE)).orElse(Collections.emptyList()));
        transactionMetadata.setContentType(Optional.ofNullable(headers.get(HttpHeaders.CONTENT_TYPE)).orElse(Collections.emptyList()));
        transactionMetadata.setCookie(Optional.ofNullable(headers.get(HttpHeaders.COOKIE)).orElse(Collections.emptyList()));
        transactionMetadata.setHost(Optional.ofNullable(headers.get(HttpHeaders.HOST)).orElse(Collections.emptyList()));
        transactionMetadata.setIfMatch(Optional.ofNullable(headers.get(HttpHeaders.IF_MATCH)).orElse(Collections.emptyList()));
        transactionMetadata.setIfModifiedSince(Optional.ofNullable(headers.get(HttpHeaders.IF_MODIFIED_SINCE)).orElse(Collections.emptyList()));
        transactionMetadata.setIfNoneMatch(Optional.ofNullable(headers.get(HttpHeaders.IF_NONE_MATCH)).orElse(Collections.emptyList()));
        transactionMetadata.setIfRange(Optional.ofNullable(headers.get(HttpHeaders.IF_RANGE)).orElse(Collections.emptyList()));
        transactionMetadata.setIfUnmodifiedSince(Optional.ofNullable(headers.get(HttpHeaders.IF_UNMODIFIED_SINCE)).orElse(Collections.emptyList()));
        transactionMetadata.setRange(Optional.ofNullable(headers.get(HttpHeaders.RANGE)).orElse(Collections.emptyList()));
        transactionMetadata.setUserAgent(Optional.ofNullable(headers.get(HttpHeaders.USER_AGENT)).orElse(Collections.emptyList()));
        addCustomTransactionMetadata(headers);
        request.setTransactionMetadata(transactionMetadata);
    }

    /**
     * Used to add custom headers to the {@link TransactionMetadata} object.
     */
    protected void addCustomTransactionMetadata(HttpHeaders headers, Map<String, Objects> additionalMetadata) {
    }

    // Extract the Authorization header, eliminate the Bearer from the token.
    // Take the claims and make it a map of key and value.



}
