package com.alexmail.cronClient.Listeners;

import com.alexmail.cronClient.HttpRequest;
import com.alexmail.cronClient.RequestManager;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TranslateButtonListener implements ActionListener {

    private static Logger LOGGER = LogManager.getLogger(TranslateButtonListener.class.getSimpleName());
    private JTextArea cronArea;
    private JTextArea humanArea;

    public TranslateButtonListener(JTextArea cronArea, JTextArea humanArea) {
        this.cronArea = cronArea;
        this.humanArea = humanArea;
    }

    /**
     * Sends a request to translate a message
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Request request = new Request(RequestType.TRANSLATE);
        if (!cronArea.getText().isEmpty() && !humanArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (!cronArea.getText().isEmpty()) {
            request.setInputMsg(cronArea.getText());
            request.setCronMsg(true);
        } else {
            request.setInputMsg(humanArea.getText());
            request.setCronMsg(false);
        }

        RequestManager requestManager = new HttpRequest();
        Response response = requestManager.sendRequest(request);
        LOGGER.info("The translation of the message from the server");

        if (response.getErrorMsg() != null) {
            JOptionPane.showMessageDialog(null, response.getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (request.isCronMsg()) {
            cronArea.setText(null);
            humanArea.setText(response.getOutputMsg());
            humanArea.setEditable(true);
        } else {
            humanArea.setText(null);
            cronArea.setText(response.getOutputMsg());
            cronArea.setEditable(true);
        }
    }
}
