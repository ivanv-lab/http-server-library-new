package prod;

import ru.git.ivanv_lab.server.Server;
import ru.git.ivanv_lab.server.ServerBootstrap;
import ru.git.ivanv_lab.server.routing.Router;

public class Main {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .setHost("localhost")
                .setPort(8484)
                .setExecutorThreadCount(1)
                .setRouter(new Router(
                        null,
                        null
                ));

        Server server = Server.getInstance(bootstrap);
    }
}
