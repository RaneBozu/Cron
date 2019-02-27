package com.alexmail.cron;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWorkThread extends Thread {
    private Socket socket;

    ServerWorkThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream())){

            Request request;
            Translator translator;
            History history = History.getInstance();

            request = (Request) is.readObject();
            if(request.getHeader().equals("Translate")) {
                translator = new Translator(request);
                request.setOutputMsg(translator.getTranslate());
                history.addHistory(request);
                os.writeObject(request);
            } else if (request.getHeader().equals("History")) {
                request.setHistory(history.getHistoryList());
                os.writeObject(request);
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}