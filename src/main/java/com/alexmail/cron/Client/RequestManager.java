package com.alexmail.cron.Client;

import com.alexmail.cron.DTO.ConnectionType;
import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.RequestType;
import com.alexmail.cron.DTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    /**
     * Sends a request to the Simple server
     * */
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

    /**
     * Sends a request to the HTTP server
     * */
    private void sendHttpRequest() {
        String url;
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(request);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        if(request.getRequestType().equals(RequestType.TRANSLATE)) {
            url = "http://localhost:8000/translate";
        } else {
            url = "http://localhost:8000/history";
        }
        okhttp3.Request req = new okhttp3.Request.Builder().url(url).post(body).build();

        try {
            okhttp3.Response resp = client.newCall(req).execute();

            if (resp.body() != null) {
                json = resp.body().string();
            }
            response = gson.fromJson(json, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}