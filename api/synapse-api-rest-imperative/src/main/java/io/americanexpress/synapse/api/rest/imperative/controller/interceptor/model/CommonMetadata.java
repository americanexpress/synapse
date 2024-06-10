package io.americanexpress.synapse.api.rest.imperative.controller.interceptor.model;

public class CommonMetadata<T> {
    private final String accept;
    private final String acceptCharset;
    private final String acceptEncoding;
    private final String acceptLanguage;
    private final String authorization;
    private final String cacheControl;
    private final String connection;
    private final String contentLength;
    private final String contentMD5;
    private final String contentType;
    private final String date;
    private final String expect;
    private final String from;
    private final String host;
    private final String ifMatch;
    private final String ifModifiedSince;
    private final String ifNoneMatch;
    private final String ifRange;
    private final String ifUnmodifiedSince;
    private final String maxForwards;
    private final String pragma;
    private final String proxyAuthorization;
    private final String range;
    private final String referer;
    private final String te;
    private final String upgrade;
    private final String userAgent;
    private final String via;
    private final String warning;
    private final T customMetadata;

    private CommonMetadata(Builder builder) {
        this.accept = builder.accept;
        this.acceptCharset = builder.acceptCharset;
        this.acceptEncoding = builder.acceptEncoding;
        this.acceptLanguage = builder.acceptLanguage;
        this.authorization = builder.authorization;
        this.cacheControl = builder.cacheControl;
        this.connection = builder.connection;
        this.contentLength = builder.contentLength;
        this.contentMD5 = builder.contentMD5;
        this.contentType = builder.contentType;
        this.date = builder.date;
        this.expect = builder.expect;
        this.from = builder.from;
        this.host = builder.host;
        this.ifMatch = builder.ifMatch;
        this.ifModifiedSince = builder.ifModifiedSince;
        this.ifNoneMatch = builder.ifNoneMatch;
        this.ifRange = builder.ifRange;
        this.ifUnmodifiedSince = builder.ifUnmodifiedSince;
        this.maxForwards = builder.maxForwards;
        this.pragma = builder.pragma;
        this.proxyAuthorization = builder.proxyAuthorization;
        this.range = builder.range;
        this.referer = builder.referer;
        this.te = builder.te;
        this.upgrade = builder.upgrade;
        this.userAgent = builder.userAgent;
        this.via = builder.via;
        this.warning = builder.warning;
        this.customMetadata = builder.customMetadata;
    }

    public static class Builder<T> {
        private String accept;
        private String acceptCharset;
        private String acceptEncoding;
        private String acceptLanguage;
        private String authorization;
        private String cacheControl;
        private String connection;
        private String contentLength;
        private String contentMD5;
        private String contentType;
        private String date;
        private String expect;
        private String from;
        private String host;
        private String ifMatch;
        private String ifModifiedSince;
        private String ifNoneMatch;
        private String ifRange;
        private String ifUnmodifiedSince;
        private String maxForwards;
        private String pragma;
        private String proxyAuthorization;
        private String range;
        private String referer;
        private String te;
        private String upgrade;
        private String userAgent;
        private String via;
        private String warning;
        private T customMetadata;

        public Builder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public Builder acceptCharset(String acceptCharset) {
            this.acceptCharset = acceptCharset;
            return this;
        }

        public Builder acceptEncoding(String acceptEncoding) {
            this.acceptEncoding = acceptEncoding;
            return this;
        }

        public Builder acceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
            return this;
        }

        public Builder authorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public Builder cacheControl(String cacheControl) {
            this.cacheControl = cacheControl;
            return this;
        }

        public Builder connection(String connection) {
            this.connection = connection;
            return this;
        }

        public Builder contentLength(String contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public Builder contentMD5(String contentMD5) {
            this.contentMD5 = contentMD5;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder expect(String expect) {
            this.expect = expect;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder ifMatch(String ifMatch) {
            this.ifMatch = ifMatch;
            return this;
        }

        public Builder ifModifiedSince(String ifModifiedSince) {
            this.ifModifiedSince = ifModifiedSince;
            return this;
        }

        public Builder ifNoneMatch(String ifNoneMatch) {
            this.ifNoneMatch = ifNoneMatch;
            return this;
        }

        public Builder ifRange(String ifRange) {
            this.ifRange = ifRange;
            return this;
        }

        public Builder ifUnmodifiedSince(String ifUnmodifiedSince) {
            this.ifUnmodifiedSince = ifUnmodifiedSince;
            return this;
        }

        public Builder maxForwards(String maxForwards) {
            this.maxForwards = maxForwards;
            return this;
        }

        public Builder pragma(String pragma) {
            this.pragma = pragma;
            return this;
        }

        public Builder proxyAuthorization(String proxyAuthorization) {
            this.proxyAuthorization = proxyAuthorization;
            return this;
        }

        public Builder range(String range) {
            this.range = range;
            return this;
        }

        public Builder referer(String referer) {
            this.referer = referer;
            return this;
        }

        public Builder te(String te) {
            this.te = te;
            return this;
        }

        public Builder upgrade(String upgrade) {
            this.upgrade = upgrade;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder via(String via) {
            this.via = via;
            return this;
        }

        public Builder warning(String warning) {
            this.warning = warning;
            return this;
        }

        public Builder customMetadata(T customMetadata) {
            this.customMetadata = customMetadata;
            return this;
        }

        public CommonMetadata<T> build() {
            return new CommonMetadata<>(this);
        }
    }

    public String getAccept() {
        return accept;
    }

    public String getAcceptCharset() {
        return acceptCharset;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getAuthorization() {
        return authorization;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public String getConnection() {
        return connection;
    }

    public String getContentLength() {
        return contentLength;
    }

    public String getContentMD5() {
        return contentMD5;
    }

    public String getContentType() {
        return contentType;
    }

    public String getDate() {
        return date;
    }

    public String getExpect() {
        return expect;
    }

    public String getFrom() {
        return from;
    }

    public String getHost() {
        return host;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public String getIfModifiedSince() {
        return ifModifiedSince;
    }

    public String getIfNoneMatch() {
        return ifNoneMatch;
    }

    public String getIfRange() {
        return ifRange;
    }

    public String getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    public String getMaxForwards() {
        return maxForwards;
    }

    public String getPragma() {
        return pragma;
    }

    public String getProxyAuthorization() {
        return proxyAuthorization;
    }

    public String getRange() {
        return range;
    }

    public String getReferer() {
        return referer;
    }

    public String getTe() {
        return te;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getVia() {
        return via;
    }

    public String getWarning() {
        return warning;
    }

    public T getCustomMetadata() {
        return customMetadata;
    }
}