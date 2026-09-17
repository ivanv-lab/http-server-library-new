package ru.git.ivanv_lab.server.routing.search.controller;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import ru.git.ivanv_lab.server.http.controller.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerSearcher {

    private static Map<HttpMethodPath, ControllerMethod> pathMap=new HashMap<>();

    public ControllerSearcher(){
        ControllerSearcher.pathMap = this.createPathMap();
    }

    public static Map<HttpMethodPath, ControllerMethod> getPathMap() {
        return pathMap;
    }

    /**
     * Поиск всех контроллеров системы
     * @return Возвращает все классы, помеченные аннотацией `@RequestMapping`
     */
    private List<Class> findSystemControllers(){
        List<Class> result = new ArrayList<>();

        try(ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages("prod")
                .scan()){
            for(ClassInfo classInfo: scanResult.getClassesWithAnnotation(RequestMapping.class)){
                Class<?> controller = classInfo.loadClass();
                result.add(controller);
            }
        }

        return result;
    }

    /**
     * Поиск всех методов контроллера для составления карты вида "HTTP-метод:путь":"контроллер:метод"
     * @return
     */
    private Map<HttpMethodPath, ControllerMethod> createPathMap(){
        List<Class> allControllers = findSystemControllers();
        Map<HttpMethodPath, ControllerMethod> tempPathMap = new HashMap<>();

        for(Class controller: allControllers){
            for(Method method: controller.getMethods()){
                Annotation[] methodAnnotations = method.getDeclaredAnnotations();
                ControllerMethod controllerMethod = new ControllerMethod(controller, method);
                HttpMethodPath httpMethodPath = findFirstHttpAnnotationWithPath(methodAnnotations);
                tempPathMap.put(httpMethodPath, controllerMethod);
            }
        }

        return tempPathMap;
    }

    private HttpMethodPath findFirstHttpAnnotationWithPath(Annotation[] methodAnnotations){
        for(Annotation annotation:methodAnnotations){
            if(annotation instanceof Get){
                return new HttpMethodPath(Get.method, ((Get) annotation).path());
            }
            if(annotation instanceof Post){
                return new HttpMethodPath(Post.method, ((Post) annotation).path());
            }
            if(annotation instanceof Put){
                return new HttpMethodPath(Put.method, ((Put) annotation).path());
            }
            if(annotation instanceof Patch){
                return new HttpMethodPath(Patch.method, ((Patch) annotation).path());
            }
            if(annotation instanceof Delete){
                return new HttpMethodPath(Delete.method, ((Delete) annotation).path());
            }
        }

        return null;
    }
}
