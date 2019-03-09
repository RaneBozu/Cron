package com.alexmail.cronClient;

import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.RequestType;
import com.alexmail.cronDTO.Response;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class CronGUI extends JFrame {
    private JTextArea cronArea = new JTextArea(20, 30);
    private JTextArea humanArea = new JTextArea(20, 30);
    private Integer[] num = {10, 20, 30};
    private JComboBox<Integer> numOfHistory = new JComboBox<>(num);
    private JCheckBox reverse;
    private DefaultListModel<History> listModel = new DefaultListModel<>();
    private JList<History> historyList = new JList<>(listModel);
    private RequestManager requestManager;
    private Response response;
    private Request request;

    CronGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1500, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Check");
        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        reverse = new JCheckBox("История в обратном порядке");

        JScrollPane scrollPane = new JScrollPane(historyList);
        JLabel infoLabel = new JLabel("Введите запрос: от 1 до 6 параметров.(минуты, часы, день месяца, месяц, день недели, сезон)");
        JLabel numOfHistoryLabel = new JLabel("Количество записей");

        Container container = this.getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(midPanel, BorderLayout.CENTER);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(infoLabel, BorderLayout.NORTH);

        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(cronArea, BorderLayout.NORTH);

        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        rightPanel.add(reverse, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        rightPanel.add(numOfHistoryLabel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        rightPanel.add(numOfHistory, constraints);

        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        rightPanel.add(scrollPane, constraints);

        midPanel.setBackground(Color.LIGHT_GRAY);
        midPanel.add(humanArea, BorderLayout.NORTH);
        midPanel.add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(button, BorderLayout.SOUTH);

        scrollPane.setPreferredSize(new Dimension(600, 400));
        historyList.setLayoutOrientation(JList.VERTICAL);
        humanArea.setLineWrap(true);
        humanArea.setWrapStyleWord(true);

        button.addActionListener(new ButtonListener());

        ListListener listListener = new ListListener();
        historyList.addListSelectionListener(listListener);
        Timer timer = new Timer(5000, new TimerListener(listListener));
        timer.start();
    }

    private class ButtonListener implements ActionListener {

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

            response = requestManager.sendRequest(request);

            if (response.getErrorMsg() != null) {
                JOptionPane.showMessageDialog(null, response.getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (request.isCronMsg()) {
                cronArea.setText(null);
                humanArea.setText(response.getOutputMsg());
            } else {
                humanArea.setText(null);
                cronArea.setText(response.getOutputMsg());
            }
        }
    }

    private class ListListener implements ListSelectionListener {

        /**
         * Shows the original request
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {

            if (historyList.getSelectedValue().isCronMsg()) {
                cronArea.setText(historyList.getSelectedValue().getInputMsg());
                humanArea.setText(historyList.getSelectedValue().getOutputMsg());
            } else {
                cronArea.setText(historyList.getSelectedValue().getOutputMsg());
                humanArea.setText(historyList.getSelectedValue().getInputMsg());
            }
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
            request.setNumOfHistoryRecords((int)numOfHistory.getSelectedItem());

            response = requestManager.sendRequest(request);

            listModel.clear();
            List<History> history = response.getHistoryList();
            for (History value : history) {
                listModel.addElement(value);
            }
            historyList.addListSelectionListener(listListener);
        }
    }
}