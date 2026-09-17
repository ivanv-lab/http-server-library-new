package ru.git.ivanv_lab.server;

import ru.git.ivanv_lab.server.routing.IRouter;

public class ServerBootstrap {

    private IRouter router;
    private String host;
    private int port;
    private int executorThreadCount;

    public ServerBootstrap(){}

    public ServerBootstrap setRouter(IRouter router) {
        this.router = router;
        return this;
    }

    public ServerBootstrap setHost(String host) {
        this.host = host;
        return this;
    }

    public ServerBootstrap setPort(int port) {
        this.port = port;
        return this;
    }

    public ServerBootstrap setExecutorThreadCount(int executorThreadCount) {
        this.executorThreadCount = executorThreadCount;
        return this;
    }

    public IRouter getRouter() {
        return router;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getExecutorThreadCount() {
        return executorThreadCount;
    }
}
