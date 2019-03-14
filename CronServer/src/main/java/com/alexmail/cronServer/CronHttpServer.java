package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
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
import java.util.Arrays;

public class CronHttpServer {
    private static Logger LOGGER = LogManager.getLogger(CronHttpServer.class.getSimpleName());

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            DataBaseConnection connection = DataBaseConnection.getConnection();
            server.createContext("/translate", new TranslationHandler(connection));
            server.createContext("/history", new HistoryHandler(connection));
            server.createContext("/update_history", new UpdateHistoryHandler(connection));
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private static class TranslationHandler implements HttpHandler {
        DataBaseConnection connection;

        TranslationHandler(DataBaseConnection connection) {
            this.connection = connection;
        }

        @Override
        public void handle(HttpExchange exchange) {

            LOGGER.info("Connection with the client is established");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                String json = in.readLine();
                Gson gson = new GsonBuilder().create();
                Request request = gson.fromJson(json, Request.class);
                LOGGER.info("Message translation request received");
                if(!Arrays.asList(RequestType.values()).contains(request.getRequestType()))
                    exchange.sendResponseHeaders(400, -1L);
                if(connection == null)
                    exchange.sendResponseHeaders(500, -1L);

                Response response = new ResponseManager().getResponse(request, connection);

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
        DataBaseConnection connection;

        HistoryHandler(DataBaseConnection connection) {
            this.connection = connection;
        }

        @Override
        public void handle(HttpExchange exchange) {

            LOGGER.info("Connection with the client is established");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                String json = in.readLine();
                Gson gson = new GsonBuilder().create();
                Request request = gson.fromJson(json, Request.class);
                LOGGER.info("Request for history received");
                if(!Arrays.asList(RequestType.values()).contains(request.getRequestType()))
                    exchange.sendResponseHeaders(400, -1L);
                if(connection == null)
                    exchange.sendResponseHeaders(500, -1L);

                Response response = new ResponseManager().getResponse(request, connection);

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
        DataBaseConnection connection;

        UpdateHistoryHandler(DataBaseConnection connection) {
            this.connection = connection;
        }

        @Override
        public void handle(HttpExchange exchange) {

            LOGGER.info("Connection with the client is established");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
                 OutputStream os = exchange.getResponseBody()) {
                String json = in.readLine();
                Gson gson = new GsonBuilder().create();
                Request request = gson.fromJson(json, Request.class);
                LOGGER.info("Request for update history received");
                if(!Arrays.asList(RequestType.values()).contains(request.getRequestType()))
                    exchange.sendResponseHeaders(400, -1L);
                if(connection == null)
                    exchange.sendResponseHeaders(500, -1L);

                Response response = new ResponseManager().getResponse(request, connection);

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