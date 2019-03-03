package com.alexmail.cron.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class CronHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/translate", new TranslateHandler());
        server.createContext("/history", new HistoryHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class TranslateHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange){
            ResponseManager responseManager = new ResponseManager();
            responseManager.translateResponse(exchange);
        }
    }

    private static class HistoryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            ResponseManager responseManager = new ResponseManager();
            responseManager.historyResponse(exchange);
        }
    }
}