package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {
    private static Logger LOGGER = LogManager.getLogger(RequestHandler.class.getSimpleName());
    private Socket socket;
    private DataBaseConnection connection;

    RequestHandler(Socket socket, DataBaseConnection connection) {
        this.socket = socket;
        this.connection = connection;
    }

    public void run() {
        try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {

            Request request = (Request) is.readObject();
            Response response = new ResponseManager().getResponse(request, connection);
            LOGGER.info("Request received");

            os.writeObject(response);
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("The response is sent");
    }
}