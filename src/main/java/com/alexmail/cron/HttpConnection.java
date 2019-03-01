package com.alexmail.cron;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection implements Connection {

    @Override
    public Request setConnection(Request request) {
        URL url;
        try {
            url = new URL("http://localhost:8000/index");
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            ObjectOutputStream out = new ObjectOutputStream(urlCon.getOutputStream());
            out.writeObject(request);
            out.close();

            ObjectInputStream ois = new ObjectInputStream(urlCon.getInputStream());
            request = (Request) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }
}

