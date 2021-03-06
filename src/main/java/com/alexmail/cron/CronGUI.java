package com.alexmail.cron;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;

class CronGUI extends JFrame {
    private JTextArea cronArea = new JTextArea(20, 30);
    private JTextArea humanArea = new JTextArea(20, 30);
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> historyList = new JList<>(listModel);

    CronGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1300, 500);
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

        scrollPane.setPreferredSize(new Dimension(500, 400));
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

            Request request = new Request("Translate");
            if (!cronArea.getText().isEmpty() && !humanArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!cronArea.getText().isEmpty()) {
                request.setInputMsg(cronArea.getText());
                request.setUserInput(cronArea.getText());
                request.setCronMsg(true);
            } else {
                request.setInputMsg(humanArea.getText());
                request.setUserInput(humanArea.getText());
                request.setCronMsg(false);
            }

            Connection connection = new Connection();
            request = connection.setConnection(request);

            if(request.isErrorMsg()) {
                JOptionPane.showMessageDialog(null, request.getOutputMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (request.isCronMsg()) {
                cronArea.setText(null);
                humanArea.setText(request.getOutputMsg());
            } else {
                humanArea.setText(null);
                cronArea.setText(request.getOutputMsg());
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
                cronArea.setText(value[0]);
                humanArea.setText(value[1]);
            } else {
                value = historyList.getSelectedValue().split(" <- ");
                cronArea.setText(value[0]);
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
            listModel.clear();
            Request request = new Request("History");
            Connection connection = new Connection();
            request = connection.setConnection(request);

            CopyOnWriteArrayList<String> history = request.getHistoryList();
            for (String str : history) {
                listModel.addElement(str);
            }
            historyList.addListSelectionListener(listListener);
        }
    }
}