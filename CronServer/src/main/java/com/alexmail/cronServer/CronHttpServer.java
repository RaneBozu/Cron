package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class CronHttpServer {
    private static Logger LOGGER = LogManager.getLogger(CronHttpServer.class.getSimpleName());
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            DataBaseConnection.connect();
            server.createContext("/translate", new TranslationHandler());
            server.createContext("/history", new HistoryHandler());
            server.createContext("/update_history", new UpdateHistoryHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Connection with the database is established");
    }

    private static class TranslationHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {

            Gson gson = new GsonBuilder().create();
            String json;
            Request request;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                json = in.readLine();
                request = gson.fromJson(json, Request.class);
                LOGGER.info("Message translation request received");

                Response response = new TranslationResponse(request).getResponse();

                byte[] buffer = gson.toJson(response).getBytes();
                exchange.sendResponseHeaders(200, buffer.length);
                os.write(buffer);
            } catch (IOException e) {
                LOGGER.error(e);
            }
            LOGGER.info("The response to the translated message is sent");
        }
    }

    private static class HistoryHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {

            Gson gson = new GsonBuilder().create();
            String json;
            Request request;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                json = in.readLine();
                request = gson.fromJson(json, Request.class);
                LOGGER.info("Request for history received");

                Response response = new HistoryResponse(request).getResponse();

                byte[] buffer = gson.toJson(response).getBytes();
                exchange.sendResponseHeaders(200, buffer.length);
                os.write(buffer);
            } catch (IOException e) {
                LOGGER.error(e);
            }
            LOGGER.info("The response with history is sent");
        }
    }

    private static class UpdateHistoryHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {
            Gson gson = new GsonBuilder().create();
            String json;
            Request request;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                json = in.readLine();
                request = gson.fromJson(json, Request.class);
                LOGGER.info("Request for update history received");

                Response response = new UpdateHistoryResponse(request).getResponse();

                byte[] buffer = gson.toJson(response).getBytes();
                exchange.sendResponseHeaders(200, buffer.length);
                os.write(buffer);
            } catch (IOException e) {
                LOGGER.error(e);
            }
            LOGGER.info("The response with history is sent");
        }
    }
}