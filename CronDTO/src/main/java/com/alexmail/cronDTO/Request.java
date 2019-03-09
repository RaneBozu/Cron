package com.alexmail.cronDTO;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;
    private boolean isCronMsg;
    private String inputMsg;
    private boolean reverseIsSelected;
    private int historyID;

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public boolean isReverseIsSelected() {
        return reverseIsSelected;
    }

    public void setReverseIsSelected(boolean reverseIsSelected) {
        this.reverseIsSelected = reverseIsSelected;
    }

    public Request(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setCronMsg(boolean cronMsg) {
        this.isCronMsg = cronMsg;
    }

    public boolean isCronMsg() {
        return isCronMsg;
    }

    public void setInputMsg(String inputMsg) {
        this.inputMsg = inputMsg;
    }

    public String getInputMsg() {
        return inputMsg;
    }
}