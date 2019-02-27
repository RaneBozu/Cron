package com.alexmail.cron;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Connection {
    Request setConnection(Request request){

        try (Socket socket = new Socket("127.0.0.1", 8050);
             ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){

            os.writeObject(request);
            request = (Request) is.readObject();

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return request;
    }
}
