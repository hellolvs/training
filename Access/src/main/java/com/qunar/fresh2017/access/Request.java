package com.qunar.fresh2017.access;

import java.net.URI;

/**
 * 请求类，封装每条请求
 * Created by shuai.lv on 2017/3/1.
 */
public class Request {
    private String method;
    private URI uri;

    public Request(String method, String uri) {
        this.method = method;
        this.uri = URI.create(uri);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
