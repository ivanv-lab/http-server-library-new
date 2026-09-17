package ru.git.ivanv_lab.server.http.request;

import java.util.List;
import java.util.Map;

public class HttpRequest {
    private final HttpMethod method;
    private final String path;
    private final Map<String, List<String>> headers;
    private final String body;

    public HttpRequest(HttpMethod method, String path, Map<String, List<String>> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString(){
        return "{ method: %s; path: %s; body: %s }"
                .formatted(method, path, body);
    }
}
