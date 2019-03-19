package com.alexmail.cronClient.GUI.Listeners;

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


public class UpdateButtonListener implements ActionListener {
    private static Logger LOGGER = LogManager.getLogger(UpdateButtonListener.class.getSimpleName());

    private JTextArea cronArea;
    private JTextArea humanArea;

    public UpdateButtonListener(JTextArea cronArea, JTextArea humanArea) {
        this.cronArea = cronArea;
        this.humanArea = humanArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Request request = new Request(RequestType.UPDATE_HISTORY);
        if (ListListener.getCurrentHistoryID() == 0 || cronArea.isEditable() && humanArea.isEditable()) {
            JOptionPane.showMessageDialog(null, "Выберите запись для изменения", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (!humanArea.isEditable()) {
            request.setInputMsg(cronArea.getText());
            request.setCronMsg(true);
        } else {
            request.setInputMsg(humanArea.getText());
            request.setCronMsg(false);
        }
        request.setHistoryID(ListListener.getCurrentHistoryID());
        LOGGER.info("Request for update history completed");

        RequestManager requestManager = new HttpRequest();
        Response response = requestManager.sendRequest(request);
        LOGGER.info("History update on the server");

        if (response.getErrorMsg() != null) {
            JOptionPane.showMessageDialog(null, response.getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (request.isCronMsg()) {
            humanArea.setText(response.getOutputMsg());
            humanArea.setEditable(true);
        } else {
            cronArea.setText(response.getOutputMsg());
            cronArea.setEditable(true);
        }
    }
}
