package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class CronHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/translate", new TranslateHandler());
        server.start();
    }

    static class TranslateHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {

            byte[] bytes;
            Request request;
            Response response;

            try {
                if (exchange.getRequestMethod().equals("POST")) {
                    ObjectInputStream in = new ObjectInputStream(exchange.getRequestBody());
                    request = (Request) in.readObject();
                    ResponseManager responseManager = new ResponseManager();
                    responseManager.formingResponse(request);
                    response = responseManager.getResponse();
                    bytes = Serializer.serialize(response);
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