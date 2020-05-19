import javax.swing.*;
import java.awt.*;

public class ChatLUI extends JFrame {

    private int width = 430;
    private JTextArea dialog;
    private JTextField message;
    private JButton clear;
    private JButton send;
    private ChatClient client;

    public ChatLUI(ChatClient chatClient) throws HeadlessException {
        super();

        this.setSize(width,200);
        this.setLayout(null);
        this.setTitle(chatClient.getHost() + ":" + chatClient.getPort());

        client = chatClient;

        dialog = new JTextArea();
        dialog.setBounds(10, 10, width - 20, 100);
        dialog.setColumns(1000);
        this.add(dialog);

        message = new JTextField();
        message.setText("Type message here...");
        message.setColumns(1);
        message.setBounds(10, 120, width - 20, 20);
        this.add(message);

        clear = new JButton();
        clear.setBounds(10, 150, (width - 30) / 2, 20);
        clear.setText("Clear");
        clear.addActionListener(e -> {
            dialog.setText("");
        });
        this.add(clear);

        send = new JButton();
        send.setBounds(10 + (width - 30) / 2 + 10, 150, (width - 30) / 2, 20);
        send.setText("Send");
        send.addActionListener(e -> {
            client.send(message.getText());
            dialog.setText(dialog.getText() + "You: " + message.getText() + "\n");
            message.setText("");
        });
        this.add(send);

        this.setVisible(true);
        client.setConsole(new Console(){
            @Override
            void print(Chat.Message message) {
                dialog.setText(dialog.getText() + message.getFrom() + " [" + message.getTime() + "]: " + message.getContent() + "\n");
            }
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
