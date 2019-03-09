package com.alexmail.cronDTO;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    private String errorMsg;
    private String outputMsg;
    private List<History> historyList;

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public List<History> getHistoryList() {
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
