package com.alexmail.cronDTO;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;
    private boolean isCronMsg;
    private String inputMsg;
    private boolean reverseIsSelected;

    public int getNumOfHistoryRecords() {
        return numOfHistoryRecords;
    }

    public void setNumOfHistoryRecords(int numOfHistoryRecords) {
        this.numOfHistoryRecords = numOfHistoryRecords;
    }

    private int numOfHistoryRecords;

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