import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class ChatServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("Pridator");
        int port = 8888;
        Console console = new Console();
        String name = "loz";

        Server server = ServerBuilder.forPort(port).addService(new ChatService(console, name)).build();
        server.start();
        logger.info("Run server on " + port);
        server.awaitTermination();
    }

}
