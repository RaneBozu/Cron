package com.alexmail.cronDTO;

import java.io.Serializable;

public class History implements Serializable {
    private String inputMsg;
    private  String outputMsg;
    private boolean isCronMsg;

    public String getInputMsg() {
        return inputMsg;
    }

    public String getOutputMsg() {
        return outputMsg;
    }

    public boolean isCronMsg() {
        return isCronMsg;
    }

    public History(String inputMsg, String outputMsg, boolean isCronMsg) {
        this.inputMsg = inputMsg;
        this.outputMsg = outputMsg;
        this.isCronMsg = isCronMsg;
    }

    @Override
    public String toString() {
        return isCronMsg ? inputMsg + " -> " + outputMsg : outputMsg + " <- " + inputMsg;
    }
}
