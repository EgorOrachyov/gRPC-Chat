package edu.grpcchat;

/**
 * Wrapper for default system out stream to the console.
 * Print messages to the console UI.
 * (Can be removed with some OUT interface)
 */
public class Console {
    void print(Chat.Message message) {
        System.out.print(message.getFrom());
        System.out.print(" [" + message.getTime() + "]: ");
        System.out.println(message.getContent());
    }
}
