package io.americanexpress.synapse.api.rest.imperative.controller.interceptor.helper;

import io.americanexpress.synapse.api.rest.imperative.controller.interceptor.model.TransactionMetadata;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * {@code TransactionMetadataCreator} handles the creation of the {@link TransactionMetadata} object, to copy all the common
 * header values from {@link HttpHeaders}.
 * <p>
 * Due to the amount of fields in this class, it was opted to use the builder pattern, to maintain a clean creation of
 * the object instance, without a clutter constructor.
 *
 * @author John Robert Martinez Ponce
 */
@Component
public class TransactionMetadataCreator {

    public TransactionMetadata create(HttpServletRequest request) {
        TransactionMetadata.Builder builder = new TransactionMetadata.Builder();

        builder.accept(request.getHeader(HttpHeaders.ACCEPT));
        builder.acceptCharset(request.getHeader(HttpHeaders.ACCEPT_CHARSET));
        builder.acceptEncoding(request.getHeader(HttpHeaders.ACCEPT_ENCODING));
        builder.acceptLanguage(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE));
        builder.authorization(request.getHeader(HttpHeaders.AUTHORIZATION));
        builder.cacheControl(request.getHeader(HttpHeaders.CACHE_CONTROL));
        builder.connection(request.getHeader(HttpHeaders.CONNECTION));
        builder.contentLength(request.getHeader(HttpHeaders.CONTENT_LENGTH));
        builder.contentType(request.getHeader(HttpHeaders.CONTENT_TYPE));
        builder.date(request.getHeader(HttpHeaders.DATE));
        builder.expect(request.getHeader(HttpHeaders.EXPECT));
        builder.from(request.getHeader(HttpHeaders.FROM));
        builder.host(request.getHeader(HttpHeaders.HOST));
        builder.ifMatch(request.getHeader(HttpHeaders.IF_MATCH));
        builder.ifModifiedSince(request.getHeader(HttpHeaders.IF_MODIFIED_SINCE));
        builder.ifNoneMatch(request.getHeader(HttpHeaders.IF_NONE_MATCH));
        builder.ifRange(request.getHeader(HttpHeaders.IF_RANGE));
        builder.ifUnmodifiedSince(request.getHeader(HttpHeaders.IF_UNMODIFIED_SINCE));
        builder.maxForwards(request.getHeader(HttpHeaders.MAX_FORWARDS));
        builder.pragma(request.getHeader(HttpHeaders.PRAGMA));
        builder.proxyAuthorization(request.getHeader(HttpHeaders.PROXY_AUTHORIZATION));
        builder.range(request.getHeader(HttpHeaders.RANGE));
        builder.referer(request.getHeader(HttpHeaders.REFERER));
        builder.te(request.getHeader(HttpHeaders.TE));
        builder.upgrade(request.getHeader(HttpHeaders.UPGRADE));
        builder.userAgent(request.getHeader(HttpHeaders.USER_AGENT));
        builder.via(request.getHeader(HttpHeaders.VIA));
        builder.warning(request.getHeader(HttpHeaders.WARNING));

        return builder.build();
    }
}
