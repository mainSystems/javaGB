package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public interface Connector {
    int SERVER_PORT = 8189;
    String SERVER_HOST = "localhost";


    static void close(DataInputStream inputStream, DataOutputStream outputStream, Socket socket) {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(ErrorMessage.Cant_Close_Connect);
            e.printStackTrace();
        }
    }

}
