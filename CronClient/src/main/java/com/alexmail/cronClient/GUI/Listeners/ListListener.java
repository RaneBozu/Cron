package com.alexmail.cronClient.GUI.Listeners;

import com.alexmail.cronDTO.History;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {
    private static Logger LOGGER = LogManager.getLogger(ListListener.class.getSimpleName());
    private JTextArea cronArea;
    private JTextArea humanArea;
    private JList<History> historyList;
    private static int currentHistoryID;

    public ListListener(JTextArea cronArea, JTextArea humanArea, JList<History> historyList) {
        this.cronArea = cronArea;
        this.humanArea = humanArea;
        this.historyList = historyList;
    }

    static int getCurrentHistoryID() {
        return currentHistoryID;
    }

    /**
     * Shows the original request
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        currentHistoryID = historyList.getSelectedValue().getId();
        if (historyList.getSelectedValue().isCronMsg()) {
            cronArea.setEditable(true);
            humanArea.setEditable(false);
            cronArea.setText(historyList.getSelectedValue().getInputMsg());
            humanArea.setText(historyList.getSelectedValue().getOutputMsg());
        } else {
            humanArea.setEditable(true);
            cronArea.setEditable(false);
            cronArea.setText(historyList.getSelectedValue().getOutputMsg());
            humanArea.setText(historyList.getSelectedValue().getInputMsg());
        }
        LOGGER.info("Received information about the selected request");
    }
}
