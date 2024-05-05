package com.rmacd.models;

import java.util.Map;

public class IncomingRequest {

    SchemeEnum scheme;
    Boolean isURLEncoding;
    Boolean preserveHostHeader;
    String path;
    Integer port;
    HttpMethodEnum method;
    Map<String, String> headers;
    Map<String, String> queries;
    String host;
    String authority;

    public String getAuthority() {
        return authority;
    }

    public IncomingRequest setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public IncomingRequest setQueries(Map<String, String> queries) {
        this.queries = queries;
        return this;
    }

    public String getHost() {
        return host;
    }

    public IncomingRequest setHost(String host) {
        this.host = host;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public IncomingRequest setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public SchemeEnum getScheme() {
        return scheme;
    }

    public IncomingRequest setScheme(SchemeEnum scheme) {
        this.scheme = scheme;
        return this;
    }

    public Boolean getURLEncoding() {
        return isURLEncoding;
    }

    public IncomingRequest setURLEncoding(Boolean URLEncoding) {
        isURLEncoding = URLEncoding;
        return this;
    }

    public Boolean getPreserveHostHeader() {
        return preserveHostHeader;
    }

    public IncomingRequest setPreserveHostHeader(Boolean preserveHostHeader) {
        this.preserveHostHeader = preserveHostHeader;
        return this;
    }

    public String getPath() {
        return path;
    }

    public IncomingRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public IncomingRequest setPort(Integer port) {
        this.port = port;
        return this;
    }

    public HttpMethodEnum getMethod() {
        return method;
    }

    public IncomingRequest setMethod(HttpMethodEnum method) {
        this.method = method;
        return this;
    }

    @Override
    public String toString() {
        return "IncomingRequest{" +
                "scheme=" + scheme +
                ", isURLEncoding=" + isURLEncoding +
                ", preserveHostHeader=" + preserveHostHeader +
                ", path='" + path + '\'' +
                ", port=" + port +
                ", method=" + method +
                ", headers=" + headers +
                ", queries=" + queries +
                ", host='" + host + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
