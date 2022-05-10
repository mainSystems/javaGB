package ru.geekbrains.clientchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static final int SERVER_PORT = 8189;
    public static Socket socket = null;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            initServer(serverSocket);
            echoReply(inputStream, outputStream);

        } catch (IOException e) {
            System.err.printf("%s, PORT: %s", ErrorMessage.CANT_CONNECT_TO_SERVER ,SERVER_PORT);
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static void initServer(ServerSocket serverSocket) throws IOException {
        System.out.printf("Сервер запущен: %s%nОжидаем подключения...%n", serverSocket);
        socket = serverSocket.accept();
        System.out.println("Клиент подключился: " + socket.toString());
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    private static void echoReply(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith("/end")) {
                break;
            }
            outputStream.writeUTF(System.lineSeparator());
            outputStream.writeUTF("Echo: " + message);
        }
    }

    public static void close() {
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