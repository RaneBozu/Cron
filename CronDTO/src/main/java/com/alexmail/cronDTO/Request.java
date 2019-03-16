package com.alexmail.cronDTO;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;
    private String inputMsg;
    private boolean isCronMsg;
    private boolean reverseIsSelected;
    private boolean isTimePeriodSelected;
    private String historyStartDate;
    private String historyEndDate;
    private int numOfHistory;
    private int historyID;

    public boolean isTimePeriodSelected() {
        return isTimePeriodSelected;
    }

    public void setTimePeriodSelected(boolean timePeriodSelected) {
        isTimePeriodSelected = timePeriodSelected;
    }

    public String getHistoryStartDate() {
        return historyStartDate;
    }

    public void setHistoryStartDate(String historyStartDate) {
        this.historyStartDate = historyStartDate;
    }

    public String getHistoryEndDate() {
        return historyEndDate;
    }

    public void setHistoryEndDate(String historyEndDate) {
        this.historyEndDate = historyEndDate;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getNumOfHistory() {
        return numOfHistory;
    }

    public void setNumOfHistory(int numOfHistory) {
        this.numOfHistory = numOfHistory;
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