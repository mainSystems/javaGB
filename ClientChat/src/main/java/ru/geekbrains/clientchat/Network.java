package ru.geekbrains.clientchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {

    protected static final String SERVER_ADDR = "localhost";
    protected static final int SERVER_PORT = 8189;
    private Socket socket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String host;
    private int port;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network() {
        this(SERVER_ADDR, SERVER_PORT);
    }

    public boolean connect() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.err.printf("Network module: %s: %s%n", ErrorMessage.Cant_Connect_to_server, SERVER_ADDR);
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            System.err.println(ErrorMessage.Cant_Send_Message_To_Server);
            e.printStackTrace();
            throw e;
        }
    }

    public void waitMessage(Consumer<String> messageHandler) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inputStream.readUTF().equalsIgnoreCase("/end")) {
                            break;
                        }
                        String message = inputStream.readUTF();
                        messageHandler.accept(message);
//                        messageArea.append(inputStream.readUTF());
//                        messageArea.appendText(System.lineSeparator());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(ErrorMessage.Cant_Close_Connect_to_server);
            e.printStackTrace();
        }
    }
}
