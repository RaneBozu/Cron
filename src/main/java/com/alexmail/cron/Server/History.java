package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.Response;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class History {
    private static History ourInstance = new History();
    private CopyOnWriteArrayList<String> historyList = new CopyOnWriteArrayList<>();

    private History() {
    }

    List<String> getHistoryList() {
        return historyList;
    }

    static History getInstance() {
        return ourInstance;
    }

    void addHistory(Request request, Response response) {
        if(request.isCronMsg())
            historyList.add(request.getInputMsg() + " -> " + response.getOutputMsg());
        else
            historyList.add(response.getOutputMsg() + " <- " + request.getInputMsg());
    }
}
