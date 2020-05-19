import io.grpc.stub.StreamObserver;


public class ChatService extends ChatGrpc.ChatImplBase {
    Console console;
    String partner;

    public ChatService(Console console, String partner) {
        this.console = console;
        this.partner = partner;
    }

    @Override
    public void send(ChatOuterClass.Message request, StreamObserver<ChatOuterClass.Result> responseObserver) {
        String content = request.getContent();
        console.print(partner, content);
        responseObserver.onNext(ChatOuterClass.Result.newBuilder().build());
    }

}
