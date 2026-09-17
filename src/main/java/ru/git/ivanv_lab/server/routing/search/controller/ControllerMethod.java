package ru.git.ivanv_lab.server.routing.search.controller;

import java.lang.reflect.Method;

public record ControllerMethod(Class controller, Method method) {
}
