package com.alexmail.cronServer;

import java.io.IOException;
import java.net.ServerSocket;

public class CronServer {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8050)) {
            while (true) {
                new RequestHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}