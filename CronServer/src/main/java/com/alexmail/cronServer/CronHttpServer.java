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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CronHttpServer {
    private static Logger LOGGER = LogManager.getLogger(CronHttpServer.class.getSimpleName());
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
            Statement statement = conn.createStatement();
            server.createContext("/translate", new TranslationHandler(statement));
            server.createContext("/history", new HistoryHandler(statement));
            server.createContext("/update_history", new UpdateHistoryHandler(statement));
            server.setExecutor(null);
            server.start();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Connection with the database is established");
    }

    private static class TranslationHandler implements HttpHandler {
        Statement statement;

        TranslationHandler(Statement statement) {
            this.statement = statement;
        }

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

                Response response = new TranslationResponse(request).getResponse(statement);

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
        Statement statement;

        HistoryHandler(Statement statement) {
            this.statement = statement;
        }

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

                Response response = new HistoryResponse(request).getResponse(statement);

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
        Statement statement;
        UpdateHistoryHandler(Statement statement) {
            this.statement = statement;
        }

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

                Response response = new UpdateHistoryResponse(request).getResponse(statement);

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