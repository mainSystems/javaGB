package ru.geekbrains.clientchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {
    private static Network INSTANCE;

    protected static final String SERVER_ADDR = "127.0.0.1";
    protected static final int SERVER_PORT = 8189;
    private Socket socket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String host;
    private int port;

    private Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Network() {
        this(SERVER_ADDR, SERVER_PORT);
    }

    public static Network getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Network();
        }
        return INSTANCE;
    }

    public boolean connect() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.err.printf("Network module: %s: %s%n", ErrorMessage.CANT_CONNECT_TO_SERVER, SERVER_ADDR);
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            System.err.println(ErrorMessage.CANT_SEND_MESSAGE_TO_SERVER);
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
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        if (inputStream.readUTF().equalsIgnoreCase("/end")) {
                            close();
                            break;
                        }
                        String message = inputStream.readUTF();
                        messageHandler.accept(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(ErrorMessage.CANT_CLOSE_CONNECT_TO_SERVER);
            e.printStackTrace();
        }
    }
}