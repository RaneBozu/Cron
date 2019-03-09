package com.alexmail.cronClient;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SimpleRequest implements RequestManager {

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
            response = (Response) is.readObject();

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return response;
    }
}
