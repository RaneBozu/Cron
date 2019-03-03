package com.alexmail.cron.DTO;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;
    private ConnectionType connectionType;
    private boolean isCronMsg;
    private String inputMsg;

    public Request(RequestType requestType, ConnectionType connectionType) {
        this.connectionType = connectionType;
        this.requestType = requestType;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
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