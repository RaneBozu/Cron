package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestHandler extends Thread {
    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
             Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
             Statement statement = conn.createStatement()) {

            Request request;
            Response response;
            request = (Request) is.readObject();
            if (request.getRequestType().equals(RequestType.TRANSLATE)) {
                response = new TranslationResponse(request).getResponse(statement);
                os.writeObject(response);
            }else {
                response = new HistoryResponse(request).getResponse(statement);
                os.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}