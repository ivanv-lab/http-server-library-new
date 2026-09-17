package ru.git.ivanv_lab.server.routing;

import ru.git.ivanv_lab.server.http.request.HttpRequest;
import ru.git.ivanv_lab.server.http.response.HttpResponse;

public interface IRouter {
    HttpResponse routeRequest(HttpRequest request);
}
