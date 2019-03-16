package com.alexmail.cronClient.Listeners;

import com.alexmail.cronClient.HttpRequest;
import com.alexmail.cronClient.RequestManager;
import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TimerListener implements ActionListener {
    private static Logger LOGGER = LogManager.getLogger(TimerListener.class.getSimpleName());
    private JComboBox<Integer> numOfHistory;
    private DefaultListModel<History> listModel;
    private JList<History> historyList;
    private JSpinner startDate;
    private JSpinner endDate;
    private JCheckBox timePeriod;
    private JCheckBox reverse;
    private ListListener listListener;

    public TimerListener(JComboBox<Integer> numOfHistory, DefaultListModel<History> listModel, JList<History> historyList, JSpinner startDate, JSpinner endDate, JCheckBox timePeriod, JCheckBox reverse, ListListener listListener) {
        this.numOfHistory = numOfHistory;
        this.listModel = listModel;
        this.historyList = historyList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timePeriod = timePeriod;
        this.reverse = reverse;
        this.listListener = listListener;
    }

    /**
     * Sends a request to show history
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        historyList.removeListSelectionListener(listListener);
        Request request = new Request(RequestType.HISTORY);
        request.setReverseIsSelected(reverse.isSelected());
        request.setNumOfHistory((int) numOfHistory.getSelectedItem());
        request.setTimePeriodSelected(timePeriod.isSelected());
        request.setHistoryStartDate(startDate.getValue().toString());
        request.setHistoryEndDate(endDate.getValue().toString());

        RequestManager requestManager = new HttpRequest();
        Response response = requestManager.sendRequest(request);

        listModel.clear();
        List<History> history = response.getHistoryList();
        for (History value : history) {
            listModel.addElement(value);
        }
        historyList.addListSelectionListener(listListener);
        LOGGER.info("History of translations from the server was received");
    }
}
