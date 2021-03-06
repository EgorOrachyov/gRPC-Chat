package edu.grpcchat;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Run chat client thought UI.
 * All the configuration (run as client or server) is done via main args.
 */
public class MainUI {

    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("edu.grpcchat.MainUI");
        ChatServer server;
        ChatClient client;

        String whoami;
        String host = "localhost";
        int port;

        if (args.length == 2) {
            whoami = args[0];
            port = Integer.valueOf(args[1]);
            server = new ChatServer(port);
            server.run();
            logger.info("Run as server");
        }
        else if (args.length == 3) {
            whoami = args[0];
            host = args[1];
            port = Integer.valueOf(args[2]);
            logger.info("Run as client");
        }
        else {
            throw new IOException("Invalid args count");
        }

        client = new ChatClient(whoami, host, port);
        client.connect();
        new ChatLUI(client);
    }

}
