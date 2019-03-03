package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

class ResponseManager {

    private Response response = new Response();
    private History history = History.getInstance();
    private Gson gson = new GsonBuilder().create();

    /**
     * Generates a response to a translation request
     * */
    void translateResponse(HttpExchange exchange) {
        String json;
        Request request;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            json = in.readLine();
            request = gson.fromJson(json, Request.class);
            Translator translator = new Translator(request, response);
            response = translator.getResponse();
            history.addHistory(request, response);
            sendResponse(exchange);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response to a history request
     * */
    void historyResponse(HttpExchange exchange) {
        response.setHistoryList(history.getHistoryList());
        sendResponse(exchange);
    }

    private void sendResponse(HttpExchange exchange) {
        try {
            byte[] buffer = gson.toJson(response).getBytes();
            exchange.sendResponseHeaders(200, buffer.length);
            OutputStream os = exchange.getResponseBody();
            os.write(buffer);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}