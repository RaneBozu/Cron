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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class ResponseManager {

    private Response response = new Response();
    private Gson gson = new GsonBuilder().create();

    /**
     * Generates a response to a translation request
     */
    void translateResponse(HttpExchange exchange) {
        String json;
        Request request;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
             Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
             Statement statement = conn.createStatement()) {
            json = in.readLine();
            request = gson.fromJson(json, Request.class);
            Translator translator = new Translator(request, response);
            response = translator.getResponse();
            if(request.isCronMsg()) {
                statement.executeUpdate("INSERT into history values (default, '" + request.getInputMsg() + " -> " + response.getOutputMsg() + "', default)");
            } else {
                statement.executeUpdate("INSERT into history values (default, '" + response.getOutputMsg() + " <- " + request.getInputMsg() + "', default)");
            }
            sendResponse(exchange);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response to a history request
     */
    void historyResponse(HttpExchange exchange) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
             Statement statement = conn.createStatement()) {
            List<String> history = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * from history");
            while (resultSet.next()){
                history.add(resultSet.getString(3) + " : " + resultSet.getString(2));
            }
            response.setHistoryList(history);
            sendResponse(exchange);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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