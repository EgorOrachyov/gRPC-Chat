import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClient {

    private Console console = new Console();
    private Channel channel;
    private String whoami;
    private String host;
    private int port;
    private ChatServiceGrpc.ChatServiceBlockingStub blockingStub;
    private ChatServiceGrpc.ChatServiceStub asyncStub;
    private StreamObserver<Chat.Void> resultStreamObserver;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    ChatClient(String whoami, String host, int port) {
        this.whoami = whoami;
        this.host = host;
        this.port = port;

        resultStreamObserver = new StreamObserver<Chat.Void>() {
            @Override
            public void onNext(Chat.Void value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    void connect() {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = ChatServiceGrpc.newBlockingStub(channel);
        asyncStub = ChatServiceGrpc.newStub(channel);

        Chat.Connect connect = Chat.Connect.newBuilder().setIam(whoami).build();
        asyncStub.connect(connect, new StreamObserver<Chat.Message>() {
            @Override
            public void onNext(Chat.Message value) {
                console.print(value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    void send(String text) {
        String time = formatter.format(new Date());
        Chat.Message message = Chat.Message.newBuilder().setContent(text).setFrom(whoami).setTime(time).build();
        asyncStub.send(message, resultStreamObserver);
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
