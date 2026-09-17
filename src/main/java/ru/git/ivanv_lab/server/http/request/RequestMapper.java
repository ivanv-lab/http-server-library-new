package ru.git.ivanv_lab.server.http.request;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMapper {

    /**
     * Маппинг HttpExchange в сущность запроса HttpRequest
     * @param exchange Полученный на сервере HttpExchange
     * @return
     */
    public static HttpRequest map(HttpExchange exchange) {
        try {
            HttpMethod method = HttpMethod.valueOf(exchange.getRequestMethod());
            String path = exchange.getRequestURI().getPath();
            Map<String, List<String>> headers = new HashMap<>(exchange.getRequestHeaders());
            String body = new String(exchange.getRequestBody().readAllBytes());

            return new HttpRequest(method, path, headers, body);
        } catch (IOException e) {
            throw new RuntimeException("Error on mapping Request: " + e.getMessage());
        }
    }
}
