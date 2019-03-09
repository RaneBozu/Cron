package com.alexmail.cronClient;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.IOException;

public class HttpRequest implements RequestManager {

    /**
     * Sends a request to the HTTP server
     * */
    @Override
    public Response sendRequest(Request request) {
        Response response = new Response();
        String url = "http://localhost:8000/" + request.getRequestType().getType();
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(request);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        
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
        return response;
    }
}