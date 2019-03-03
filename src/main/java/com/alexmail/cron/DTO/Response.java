package com.alexmail.cron.DTO;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    private String errorMsg;
    private String outputMsg;
    private List<String> historyList;

    public void setHistoryList(List<String> historyList) {
        this.historyList = historyList;
    }

    public List<String> getHistoryList() {
        return historyList;
    }

    public void setOutputMsg(String outputMsg) {
        this.outputMsg = outputMsg;
    }

    public String getOutputMsg() {
        return outputMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
