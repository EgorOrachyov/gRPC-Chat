package edu.grpcchat;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Run gRPC message service on the server side.
 * Uses separate thread for the service.
 */
public class ChatServer {

    private Logger logger = Logger.getLogger("edu.grpcchat.ChatServer");
    private int port;
    private Server server;

    public ChatServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new ChatService()).build();
    }

    void run() {
        Thread thread = new Thread(() -> {
                try {
                    server.start();
                    server.awaitTermination();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception on server " + port);
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    logger.log(Level.WARNING, "Exception on server " + port);
                    e.printStackTrace();
                }
        });
        thread.start();
    }

}
