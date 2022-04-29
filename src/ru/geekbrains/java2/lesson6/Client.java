package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Connector {
    public static Socket socket = null;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    public static void main(String[] args) throws IOException {
        try {

            socket = new Socket(SERVER_HOST, SERVER_PORT);
            initClient(socket);
            connect(inputStream, outputStream);

        } catch (IOException e) {
            System.err.printf("%s, %s:%s", ErrorMessage.Cant_Connect_to_server, SERVER_HOST, SERVER_PORT);
            e.printStackTrace();
        } finally {
            Connector.close(inputStream, outputStream, socket);
        }
    }

    private static void initClient(Socket socket) throws IOException {
        System.out.println("Client connecting: " + socket.toString());
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    private static void connect(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = inputStream.readUTF();
                        if (message.startsWith("/end")) {
                            break;
                        }
                        System.out.println("From server: " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }

            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            outputStream.writeUTF(message);
            if (message.startsWith("/end")) {
                break;
            }
        }
    }
}