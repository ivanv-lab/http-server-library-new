package ru.git.ivanv_lab.server.routing;

import ru.git.ivanv_lab.server.filter.IFilter;
import ru.git.ivanv_lab.server.http.request.HttpRequest;
import ru.git.ivanv_lab.server.http.response.HttpResponse;
import ru.git.ivanv_lab.server.middleware.IMiddleware;
import ru.git.ivanv_lab.server.routing.search.controller.ControllerMethod;
import ru.git.ivanv_lab.server.routing.search.controller.ControllerSearcher;
import ru.git.ivanv_lab.server.routing.search.controller.HttpMethodPath;

import java.util.Map;
import java.util.Set;

public class Router implements IRouter{

    private final ControllerSearcher controllerSearcher = new ControllerSearcher();
    private final Set<IMiddleware> middlewares;
    private final Set<IFilter> filters;

    public Router(Set<IMiddleware> middlewares, Set<IFilter> filters) {
        this.middlewares = middlewares;
        this.filters = filters;
    }

    @Override
    public HttpResponse routeRequest(HttpRequest request) {
        Map<HttpMethodPath, ControllerMethod> pathMap = controllerSearcher.getPathMap();
        ControllerMethod targetMethod = pathMap.get(new HttpMethodPath(request.getMethod(), request.getPath()));

        targetMethod.method().invoke();
    }
}
