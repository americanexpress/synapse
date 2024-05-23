package io.americanexpress.synapse.service.imperative.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@code TransactionMetadata} object that it's used to expose the transaction metadata from the API module to the Service,
 * for further processing.
 *
 * @author John Robert Martinez Ponce
 */
public class TransactionMetadata {

    private List<String> contentType;
    private List<String> accept;
    private List<String> authorization;
    private List<String> acceptLanguage;
    private List<String> cacheControl;
    private List<String> ifMatch;
    private List<String> ifNoneMatch;
    private List<String> ifModifiedSince;
    private List<String> ifUnmodifiedSince;
    private List<String> ifRange;
    private List<String> range;
    private List<String> userAgent;
    private List<String> host;
    private List<String> contentEncoding;
    private List<String> contentLanguage;
    private List<String> cookie;
    private Map<String, Objects> additionalMetadata;


    public List<String> getContentType() {
        return contentType;
    }

    public void setContentType(List<String> contentType) {
        this.contentType = contentType;
    }

    public List<String> getAccept() {
        return accept;
    }

    public void setAccept(List<String> accept) {
        this.accept = accept;
    }

    public List<String> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<String> authorization) {
        this.authorization = authorization;
    }

    public List<String> getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(List<String> acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public List<String> getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(List<String> cacheControl) {
        this.cacheControl = cacheControl;
    }

    public List<String> getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(List<String> ifMatch) {
        this.ifMatch = ifMatch;
    }

    public List<String> getIfNoneMatch() {
        return ifNoneMatch;
    }

    public void setIfNoneMatch(List<String> ifNoneMatch) {
        this.ifNoneMatch = ifNoneMatch;
    }

    public List<String> getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(List<String> ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

    public List<String> getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    public void setIfUnmodifiedSince(List<String> ifUnmodifiedSince) {
        this.ifUnmodifiedSince = ifUnmodifiedSince;
    }

    public List<String> getIfRange() {
        return ifRange;
    }

    public void setIfRange(List<String> ifRange) {
        this.ifRange = ifRange;
    }

    public List<String> getRange() {
        return range;
    }

    public void setRange(List<String> range) {
        this.range = range;
    }

    public List<String> getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(List<String> userAgent) {
        this.userAgent = userAgent;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public List<String> getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(List<String> contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public List<String> getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(List<String> contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public List<String> getCookie() {
        return cookie;
    }

    public void setCookie(List<String> cookie) {
        this.cookie = cookie;
    }

    public Map<String, Objects> getAdditionalMetadata() {
        return additionalMetadata;
    }

    public void setAdditionalMetadata(Map<String, Objects> additionalMetadata) {
        this.additionalMetadata = additionalMetadata;
    }
}
