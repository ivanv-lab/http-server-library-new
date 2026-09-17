package ru.git.ivanv_lab.server.http.controller.annotation;

import ru.git.ivanv_lab.server.http.request.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Patch {
    HttpMethod method = HttpMethod.PATCH;
    String path() default "/";
}
