package ru.git.ivanv_lab.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.git.ivanv_lab.server.http.request.HttpRequest;
import ru.git.ivanv_lab.server.http.request.RequestMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private HttpServer httpServer;
    private ExecutorService executorService;
    private static volatile Server instance;

    private final ServerBootstrap bootstrap;

    private Server(ServerBootstrap bootstrap) {
        this.bootstrap = bootstrap;

        try {
            InetSocketAddress socketAddress = new InetSocketAddress(bootstrap.getHost(), bootstrap.getPort());
            httpServer = HttpServer.create(socketAddress, 0);

            executorService = Executors.newFixedThreadPool(bootstrap.getExecutorThreadCount());
            httpServer.setExecutor(executorService);

            httpServer.createContext("/", Server::handle);
            httpServer.start();

            log.info("Starting server on {}:{}/",
                    bootstrap.getHost(), bootstrap.getPort());
        } catch (IOException e) {
            throw new RuntimeException("Error on instancing Server: " + e.getMessage());
        }
    }

    private static void handle(HttpExchange exchange){
        HttpRequest request = RequestMapper.map(exchange);
        log.info("Incoming request: {}", request);

        Server.instance.bootstrap.getRouter().routeRequest(request);
    }

    public static Server getInstance(ServerBootstrap bootstrap) {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null)
                    instance = new Server(bootstrap);
            }
        }
        return instance;
    }
}
