package com.cron.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CronServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8050);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try (Socket socket = serverSocket.accept();
                 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {

                String message;
                Translator translator;

                translator = (Translator) is.readObject();
                message = translator.getTranslate();

                os.writeObject(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}