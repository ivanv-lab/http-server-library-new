package prod.controllers;

import ru.git.ivanv_lab.server.http.controller.annotation.Get;
import ru.git.ivanv_lab.server.http.controller.annotation.RequestMapping;
import ru.git.ivanv_lab.server.http.response.HttpResponse;

@RequestMapping(basePath = "/users")
public class UserController {

    @Get
    public HttpResponse getAll(){
        return null;
    }

    @Get(path = "{id}")
    public HttpResponse get(int id){
        return null;
    }
}
