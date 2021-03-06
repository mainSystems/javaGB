package ru.geekbrains.server.chat;

import ru.geekbrains.server.chat.auth.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private AuthService authService;
    private final List<ClientHandler> clients = new ArrayList<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Server started: %s%nWaiting connection...%n", serverSocket);
            authService = new AuthService();
            while (true) {
                waitAndProcessClientConnection(serverSocket);
            }
        } catch (IOException e) {
            System.err.println("Failded to bind port: " + port);
        }


    }

    private void waitAndProcessClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting client connection...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.toString());

        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    protected void broadcastMessages(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    protected void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    protected void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public AuthService getAuthService() {
        return authService;
    }
}
