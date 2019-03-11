package com.alexmail.cronClient;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SimpleRequest implements RequestManager {
    private static Logger LOGGER = LogManager.getLogger(SimpleRequest.class.getSimpleName());

    /**
     * Sends a request to the Simple server
     * */
    @Override
    public Response sendRequest(Request request) {
        Response response = new Response();
        try (Socket socket = new Socket("127.0.0.1", 8050);
             ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){

            os.writeObject(request);
            LOGGER.info("The request is sent");
            response = (Response) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        LOGGER.info("The response is received");
        return response;
    }
}