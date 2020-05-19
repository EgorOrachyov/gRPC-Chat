import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;
        Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        ChatGrpc.ChatBlockingStub blockingStub = ChatGrpc.newBlockingStub(channel);
        ChatGrpc.ChatStub asyncStub = ChatGrpc.newStub(channel);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            blockingStub.send(ChatOuterClass.Message.newBuilder().setContent(input).build());
        }
    }
}
