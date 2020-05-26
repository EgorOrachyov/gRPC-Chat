import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = Logger.getLogger("Main");
        ChatServer server;
        ChatClient client;
        Scanner scanner = new Scanner(System.in);

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

        boolean stop = false;

        while (!stop) {
            String text = scanner.nextLine();

            if (text.toLowerCase().equals("$exit")) {
                stop = true;
                continue;
            }

            client.send(text);
        }
    }

}