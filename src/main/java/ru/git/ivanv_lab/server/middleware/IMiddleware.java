package ru.git.ivanv_lab.server.middleware;

import ru.git.ivanv_lab.server.http.request.HttpRequest;

@FunctionalInterface
public interface IMiddleware {
    HttpRequest execute(HttpRequest request);
}
