package com.alexmail.cronClient;

import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class CronGUI extends JFrame {
    private static Logger LOGGER = LogManager.getLogger(CronGUI.class.getSimpleName());
    private JTextArea cronArea = new JTextArea(30, 30);
    private JTextArea humanArea = new JTextArea(30, 30);
    private Integer[] num = {10, 20, 30};
    private JComboBox<Integer> numOfHistory = new JComboBox<>(num);
    private JCheckBox reverse;
    private DefaultListModel<History> listModel = new DefaultListModel<>();
    private JList<History> historyList = new JList<>(listModel);
    private RequestManager requestManager;
    private int historyID;
    private Response response;
    private Request request;

    CronGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1400, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton translateButton = new JButton("Translate");
        JButton updateButton = new JButton("Update");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        reverse = new JCheckBox("История в обратном порядке");

        JScrollPane scrollPane = new JScrollPane(historyList);
        JLabel infoLabel = new JLabel("Введите запрос: от 1 до 6 параметров.(минуты, часы, день месяца, месяц, день недели, сезон)");
        JLabel numOfHistoryLabel = new JLabel("Количество записей");

        Container container = this.getContentPane();
        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(infoLabel, BorderLayout.NORTH);

        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints midPanelConstraints = new GridBagConstraints();
        midPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        midPanelConstraints.gridx = 1;
        midPanelConstraints.gridy = 0;
        leftPanel.add(translateButton, midPanelConstraints);

        midPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        midPanelConstraints.gridheight = 2;
        midPanelConstraints.gridx = 0;
        leftPanel.add(cronArea, midPanelConstraints);


        midPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        midPanelConstraints.gridheight = 2;
        midPanelConstraints.gridx = 2;
        leftPanel.add(humanArea, midPanelConstraints);

        midPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        midPanelConstraints.gridx = 1;
        midPanelConstraints.gridy = 1;
        leftPanel.add(updateButton, midPanelConstraints);

        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints rightPanelConstraints = new GridBagConstraints();
        rightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        rightPanelConstraints.gridx = 0;
        rightPanel.add(reverse, rightPanelConstraints);

        rightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        rightPanelConstraints.weighty = 0.1;
        rightPanelConstraints.gridx = 0;
        rightPanelConstraints.gridy = 1;
        rightPanel.add(numOfHistoryLabel, rightPanelConstraints);

        rightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        rightPanelConstraints.gridx = 2;
        rightPanel.add(numOfHistory, rightPanelConstraints);

        rightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        rightPanelConstraints.weighty = 0.1;
        rightPanelConstraints.gridwidth = 3;
        rightPanelConstraints.gridx = 0;
        rightPanelConstraints.gridy = 2;
        rightPanel.add(scrollPane, rightPanelConstraints);

        scrollPane.setPreferredSize(new Dimension(600, 400));
        historyList.setLayoutOrientation(JList.VERTICAL);
        humanArea.setLineWrap(true);
        humanArea.setWrapStyleWord(true);

        translateButton.addActionListener(new TranslateButtonListener());

        updateButton.addActionListener(new UpdateButtonListener());

        ListListener listListener = new ListListener();
        historyList.addListSelectionListener(listListener);
        Timer timer = new Timer(5000, new TimerListener(listListener));
        timer.start();
    }

    private class TranslateButtonListener implements ActionListener {

        /**
         * Sends a request to translate a message
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            requestManager = new HttpRequest();
            request = new Request(RequestType.TRANSLATE);
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
            LOGGER.info("Request for translation strings completed");

            response = requestManager.sendRequest(request);
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

    private class ListListener implements ListSelectionListener {

        /**
         * Shows the original request
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {

            historyID = historyList.getSelectedValue().getId();
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

    private class TimerListener implements ActionListener {

        ListListener listListener;

        TimerListener(ListListener listListener) {
            this.listListener = listListener;
        }

        /**
         * Sends a request to show history
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            historyList.removeListSelectionListener(listListener);
            requestManager = new HttpRequest();
            request = new Request(RequestType.HISTORY);
            request.setReverseIsSelected(reverse.isSelected());
            request.setHistoryID((int) numOfHistory.getSelectedItem());

            response = requestManager.sendRequest(request);

            listModel.clear();
            List<History> history = response.getHistoryList();
            for (History value : history) {
                listModel.addElement(value);
            }
            historyList.addListSelectionListener(listListener);
            LOGGER.info("History of translations from the server was received");
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            requestManager = new HttpRequest();
            request = new Request(RequestType.UPDATE_HISTORY);
            if (historyID == 0 || cronArea.isEditable() && humanArea.isEditable()) {
                JOptionPane.showMessageDialog(null, "Выберите запись для изменения", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!humanArea.isEditable()) {
                request.setInputMsg(cronArea.getText());
                request.setCronMsg(true);
            } else {
                request.setInputMsg(humanArea.getText());
                request.setCronMsg(false);
            }
            request.setHistoryID(historyID);
            LOGGER.info("Request for update history completed");

            response = requestManager.sendRequest(request);
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
}