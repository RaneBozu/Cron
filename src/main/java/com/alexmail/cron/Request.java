package com.alexmail.cron;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

class Request implements Serializable {

    private String header;
    private String userInput;
    private boolean isCronMsg;
    private boolean errorMsg;
    private String inputMsg;
    private String outputMsg;
    private CopyOnWriteArrayList<String> history;

    Request(String header) {
        this.header = header;
    }

    void setHistory(CopyOnWriteArrayList<String> history) {
        this.history = history;
    }

    void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    void setCronMsg(boolean cronMsg) {
        this.isCronMsg = cronMsg;
    }

    void setInputMsg(String inputMsg) {
        this.inputMsg = inputMsg;
    }

    void setErrorMsg(boolean errorMsg) {
        this.errorMsg = errorMsg;
    }

    void setOutputMsg(String outputMsg) {
        this.outputMsg = outputMsg;
    }

    String getOutputMsg() {
        return outputMsg;
    }

    CopyOnWriteArrayList<String> getHistoryList() {
        return history;
    }

    String getUserInput() {
        return userInput;
    }

    String getInputMsg() {
        return inputMsg;
    }

    String getHeader() {
        return header;
    }

    boolean isCronMsg() {
        return isCronMsg;
    }

    boolean isErrorMsg() {
        return errorMsg;
    }
}