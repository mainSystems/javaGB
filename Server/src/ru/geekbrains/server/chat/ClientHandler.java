package ru.geekbrains.server.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    public static final String AUTH_COMMAND = "/auth";
    public static final String AUTH_OK_COMMAND = "/authOk";
    private MyServer server;
    private final Socket clientSocket;
    private DataInputStream inputSocket;
    private DataOutputStream outputSocket;

    public ClientHandler(MyServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        inputSocket = new DataInputStream(clientSocket.getInputStream());
        outputSocket = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authenticate();
                readMessages();
            } catch (IOException e) {
                System.err.println("Failed to read message from client");
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to cloe connection");
                }
            }
        }).start();
    }

    private void authenticate() throws IOException {
        while (true) {
            String message = inputSocket.readUTF();
            if (message.startsWith(AUTH_COMMAND)) {
                String[] parts = message.split(" ");
                String login = parts[1];
                String password = parts[2];
                String userName = this.server.getAuthService().getuserNameByLoginAndPassword(login, password);

                if (userName == null) {
                    sendMessage("Invalid username or password" + userName + ":" + password);
                } else {
                    sendMessage(String.format("%s, %s", AUTH_OK_COMMAND, userName));
                    server.subscribe(this);
                    return;
                }
            }
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = inputSocket.readUTF();
            System.out.println("message = " + message);
            if (message.startsWith("/end")) {
                return;
            } else {
                processMessage(message);
            }
        }
    }

    private void processMessage(String message) throws IOException {
        this.server.broadcastMessages(message, this);
    }

    protected void sendMessage(String message) throws IOException {
        this.outputSocket.writeUTF(message);
    }

    private void closeConnection() throws IOException {
        inputSocket.close();
        outputSocket.close();
        clientSocket.close();
        server.unsubscribe(this);
    }
}
