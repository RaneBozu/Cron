package com.alexmail.cronDTO;

import java.io.Serializable;

public class History implements Serializable {
    private int id;
    private String inputMsg;
    private  String outputMsg;
    private boolean isCronMsg;

    public History(int id, String inputMsg, String outputMsg, boolean isCronMsg) {
        this.id = id;
        this.inputMsg = inputMsg;
        this.outputMsg = outputMsg;
        this.isCronMsg = isCronMsg;
    }

    public int getId() {
        return id;
    }

    public String getInputMsg() {
        return inputMsg;
    }

    public String getOutputMsg() {
        return outputMsg;
    }

    public boolean isCronMsg() {
        return isCronMsg;
    }

    @Override
    public String toString() {
        return isCronMsg ? inputMsg + " → " + outputMsg : outputMsg + " ← " + inputMsg;
    }
}
