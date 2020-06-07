package edu.grpcchat;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

    private HashMap<String,StreamObserver<Chat.Message>> connection = new HashMap<>();
    String user0 = null;
    String user1 = null;

    @Override
    public void connect(Chat.Connect request, StreamObserver<Chat.Message> responseObserver) {
        if (connection.size() > 2) return;

        String iam = request.getIam();
        connection.put(iam, responseObserver);
        checkReady();
    }

    @Override
    public void send(Chat.Message request, StreamObserver<Chat.Void> responseObserver) {
        if (connection.size() != 2) return;

        String from = request.getFrom();
        String to = (from.equals(user0)? user1: user0);
        Optional<StreamObserver<Chat.Message>> p = Optional.ofNullable(connection.get(to));
        p.ifPresent(stream -> stream.onNext(request));
    }

    private void checkReady() {
        if (connection.size() == 2) {
            Iterator<String> iterator = connection.keySet().iterator();
            user0 = iterator.next();
            user1 = iterator.next();
        }
    }

}
