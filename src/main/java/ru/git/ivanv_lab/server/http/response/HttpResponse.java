package ru.git.ivanv_lab.server.http.response;

import java.util.List;
import java.util.Map;

public class HttpResponse {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final String body;

    public HttpResponse(int statusCode, Map<String, List<String>> headers, String body) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
