package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.RequestType;
import com.alexmail.cron.DTO.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {
    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){

            Request request;
            Response response = new Response();
            History history = History.getInstance();

            request = (Request) is.readObject();
            if(request.getRequestType().equals(RequestType.TRANSLATE)) {
                Translator translator = new Translator(request, response);
                response = translator.getResponse();
                history.addHistory(request, response);
                os.writeObject(response);
            } else if (request.getRequestType().equals(RequestType.HISTORY)) {
                response.setHistoryList(history.getHistoryList());
                os.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}