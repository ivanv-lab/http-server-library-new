package ru.git.ivanv_lab.server.routing.search.controller;

import ru.git.ivanv_lab.server.http.request.HttpMethod;

public record HttpMethodPath(HttpMethod httpMethod, String path) {
}
