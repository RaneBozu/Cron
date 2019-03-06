package com.alexmail.cron.Client;

import com.alexmail.cron.DTO.ConnectionType;
import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.RequestType;
import com.alexmail.cron.DTO.Response;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CronGUI extends JFrame {
    private JTextArea cronArea = new JTextArea(20, 30);
    private JTextArea humanArea = new JTextArea(20, 30);
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> historyList = new JList<>(listModel);
    private RequestManager requestManager = new RequestManager();
    private Response response;
    private Request request;

    public CronGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Check");
        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(historyList);
        JLabel infoLabel = new JLabel("Введите запрос: от 1 до 6 параметров.(минуты, часы, день месяца, месяц, день недели, сезон)");

        Container container = this.getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(midPanel, BorderLayout.CENTER);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(infoLabel, BorderLayout.NORTH);

        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(cronArea, BorderLayout.NORTH);

        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.add(scrollPane, BorderLayout.WEST);

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
        Timer timer = new Timer(3000, new TimerListener(listListener));
        timer.start();
    }

    private class ButtonListener implements ActionListener {

        /**
         * Sends a request to translate a message
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            request = new Request(RequestType.TRANSLATE, ConnectionType.HTTP);
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

            if(response.getErrorMsg() != null) {
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

            String[] value;
            if (historyList.getSelectedValue().contains("->")) {
                value = historyList.getSelectedValue().split(" -> ");
                cronArea.setText(value[0].substring(22));
                humanArea.setText(value[1]);
            } else {
                value = historyList.getSelectedValue().split(" <- ");
                cronArea.setText(value[0].substring(22));
                humanArea.setText(value[1]);
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
            request = new Request(RequestType.HISTORY, ConnectionType.HTTP);

            response = requestManager.sendRequest(request);

            listModel.clear();
            List<String> history = response.getHistoryList();
            for (String str : history) {
                listModel.addElement(str);
            }
            historyList.addListSelectionListener(listListener);
        }
    }
}