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

    private DataInputStream socketInput;
    private DataOutputStream socketOutput;

    private String host;
    private int port;

    private Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Network() {
        this(SERVER_ADDR, SERVER_PORT);
    }

    public static Network getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Network();
        }
        return INSTANCE;
    }

    public boolean connect() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            socketInput = new DataInputStream(socket.getInputStream());
            socketOutput = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            System.err.printf("Network module: %s: %s%n", ErrorMessage.CANT_CONNECT_TO_SERVER, SERVER_ADDR);
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) throws IOException {
        try {
            socketOutput.writeUTF(message);
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
                while (true) {
                    try {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        String message = socketInput.readUTF();
                        messageHandler.accept(message);
                    } catch (IOException e) {
                        System.err.println(ErrorMessage.FAILED_TO_GET_MESSAGE);
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            socketInput.close();
            socketOutput.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(ErrorMessage.CANT_CLOSE_CONNECT_TO_SERVER);
            e.printStackTrace();
        }
    }
}