package com.alexmail.cron.Client;

import com.alexmail.cron.DTO.ConnectionType;
import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

class RequestManager {

    private Request request;
    private Response response;

    RequestManager(Request request) {
        this.request = request;
    }

    Response getResponse() {
        return response;
    }

    void sendRequest(){

        if(request.getConnectionType().equals(ConnectionType.HTTP)){
            sendHttpRequest();
        } else {
            sendSimpleRequest();
        }
    }

    private void sendSimpleRequest() {
        try (Socket socket = new Socket("127.0.0.1", 8050);
             ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){

            os.writeObject(request);
            response = (Response) is.readObject();

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private void sendHttpRequest() {
        URL url;
        try {
            url = new URL("http://localhost:8000/translate");
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            ObjectOutputStream out = new ObjectOutputStream(urlCon.getOutputStream());
            out.writeObject(request);
            out.close();

            ObjectInputStream ois = new ObjectInputStream(urlCon.getInputStream());
            response = (Response) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}