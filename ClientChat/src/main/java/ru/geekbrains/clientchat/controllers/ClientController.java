package ru.geekbrains.clientchat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.geekbrains.clientchat.ClientChat;
import ru.geekbrains.clientchat.ErrorMessage;
import ru.geekbrains.clientchat.Network;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class ClientController {

    @FXML
    public TextField messageTextArea;
    @FXML
    public Button sendMessageButton;
    @FXML
    public TextArea chatTextArea;
    @FXML
    public ListView userList;

    private ClientChat application;

    public void sendMessage() {
        String message = messageTextArea.getText();

        if (message.isEmpty()) {
            messageTextArea.clear();
            return;
        }

        String sender = null;
        if (!userList.getSelectionModel().isEmpty()) {
            sender = userList.getSelectionModel().getSelectedItem().toString();
            System.out.println("message2: " + message);
            System.out.println("User: " + sender);
        }
        try {
            message = sender != null ? String.format("%s: %s", sender, message) : message;
            Network.getInstance().sendMessage(message);
        } catch (IOException e) {
            System.err.println(ErrorMessage.ERROR_NETWORK_COMMUNICATION);
        }

        chatTextArea.appendText(DateFormat.getDateInstance().format(new Date()) + ": ");
        messageTextArea.requestFocus();
        appendMessageToChat("Me", message);


    }

    public void appendMessageToChat(String sender, String message) {
        chatTextArea.appendText(DateFormat.getDateInstance().format(new Date()) + ": ");

        if (sender != null) {
            chatTextArea.appendText(sender + ": ");
        }
        chatTextArea.appendText(message);
        chatTextArea.appendText(System.lineSeparator());
        chatTextArea.appendText(System.lineSeparator());
        messageTextArea.requestFocus();
        messageTextArea.clear();
    }

    public void initMessageHandler() {
        Network.getInstance().waitMessage(new Consumer<String>() {
            @Override
            public void accept(String message) {
                appendMessageToChat("Server", message);
            }
        });
    }

    public ClientChat getApplication() {
        return application;
    }

    public void setApplication(ClientChat application) {
        this.application = application;
    }
}