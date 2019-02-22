package com.cron.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CronServer {
    public static void main(String[] args) {
        String[] userInput;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8050);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {

                Phrase[] allPhrase = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
                StringBuilder massage = new StringBuilder();

                userInput = (String[]) is.readObject();
                if (br.readLine().equals("1")) {
                    for (int i = 0; i <= userInput.length - 1; i++) {
                        massage.append(allPhrase[i].getHumanPhrase(userInput[i])).append(", ");
                    }
                } else {
                    for (int i = 0; i <= userInput.length - 1; i++) {
                        massage.append(allPhrase[i].getCronPhrase(userInput[i])).append(" ");
                    }
                }

                os.writeObject(massage.toString());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}