import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
        ChatServer server;
        ChatClient client;
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);

        String whoami;
        String host = "localhost";
        int port;

        writer.println("Run as server? ");
        String answer = scanner.next();

        if (answer.toLowerCase().equals("yes")) {
            writer.println("Enter your name and server port");
            whoami = scanner.next();
            port = scanner.nextInt();
            server = new ChatServer(port);
            server.run();
            logger.info("Run as server");
        }
        else {
            writer.println("Enter your name, server address and port to connect");
            whoami = scanner.next();
            host = scanner.next();
            port = scanner.nextInt();
            logger.info("Run as client");
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