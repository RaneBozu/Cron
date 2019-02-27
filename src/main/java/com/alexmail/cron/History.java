package com.alexmail.cron;

import java.util.concurrent.CopyOnWriteArrayList;

class History {
    private static History ourInstance = new History();
    private CopyOnWriteArrayList<String> historyList = new CopyOnWriteArrayList<>();


    CopyOnWriteArrayList<String> getHistoryList() {
        return historyList;
    }

    private History() {
    }

    static History getInstance() {
        return ourInstance;
    }

    void addHistory(Request request) {
        if(request.isCronMsg())
            historyList.add(request.getInputMsg() + " -> " + request.getOutputMsg());
        else
            historyList.add(request.getOutputMsg() + " <- " + request.getInputMsg());
    }
}
