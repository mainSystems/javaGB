package ru.geekbrains.clientchat.controllers;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.geekbrains.clientchat.ClientChat;
import ru.geekbrains.clientchat.ErrorMessage;
import ru.geekbrains.clientchat.Network;

import java.io.IOException;
import java.util.function.Consumer;


public class AuthController {
    private ClientChat clientChat;

    public static final String AUTH_COMMAND = "/auth";
    public static final String AUTH_OK_COMMAND = "/authOk";
    public static final String AUTH_TIMER_START = "start";
    public static final String AUTH_TIMER_STOP = "stop";

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button authButton;

    @FXML
    public void executeAuth() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            System.err.println("Login and pass must be set");
            return;
        }

        String authCommandMessage = String.format("%s %s %s", AUTH_COMMAND,login,password);

        try {
            Network.getInstance().sendMessage(authCommandMessage);
        } catch (IOException e) {
            System.err.println(ErrorMessage.ERROR_NETWORK_COMMUNICATION);
        }

    }


    public void initMessageHandler() {
        clientChat.authTimer(AUTH_TIMER_START);
        Network.getInstance().waitMessage(new Consumer<String>() {
            @Override
            public void accept(String message) {
                if (message.startsWith(AUTH_OK_COMMAND)) {
                    clientChat.authTimer(AUTH_TIMER_STOP);
                    Thread.currentThread().interrupt();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String[] parts = message.split(" ");
                            String username = parts[1];
                            clientChat.getChatStage().setTitle(username);
                            clientChat.getAuthStage().close();
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            clientChat.showErrorDialog(ErrorMessage.NO_SUCH_USER);
                        }
                    });
                }
            }
        });
    }

    public void setClientChat(ClientChat clientChat) {
        this.clientChat = clientChat;
    }
}