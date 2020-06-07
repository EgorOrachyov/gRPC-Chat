package edu.grpcchat;

public class Console {
    void print(Chat.Message message) {
        System.out.print(message.getFrom());
        System.out.print(" [" + message.getTime() + "]: ");
        System.out.println(message.getContent());
    }
}
