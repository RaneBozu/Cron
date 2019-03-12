package com.alexmail.cronServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class CronServer {
    private static Logger LOGGER = LogManager.getLogger(CronServer.class.getSimpleName());
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8050)) {
            DataBaseConnection.connect();
            while (true) {
                new RequestHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Connection with the client is established");
    }
}