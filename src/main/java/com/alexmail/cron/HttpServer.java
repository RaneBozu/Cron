package com.alexmail.cron;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new HttpServerWork());
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

    static class HttpServerWork implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {

            byte[] bytes;
            Request request;
            try {
                if (exchange.getRequestMethod().equals("POST")) {
                    ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
                    request = (Request) in.readObject();
                    Response response = new Response();
                    bytes = response.getResponse(request);
                    in.close();
                } else {
                    bytes = "Thanks for visit our site".getBytes();
                }
                exchange.sendResponseHeaders(200, bytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}