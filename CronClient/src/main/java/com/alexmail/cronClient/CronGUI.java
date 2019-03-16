package com.alexmail.cronClient;

import com.alexmail.cronClient.Listeners.*;
import com.alexmail.cronDTO.History;

import javax.swing.*;
import java.awt.*;

class CronGUI extends JFrame {

    CronGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1400, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton translateButton = new JButton("Translate");
        JLabel infoLabel = new JLabel("Введите запрос: от 1 до 6 параметров.(минуты, часы, день месяца, месяц, день недели, сезон)");
        JButton updateButton = new JButton("Update");
        JButton openFileButton = new JButton("File");
        JTextArea cronArea = new JTextArea(30, 30);
        JTextArea humanArea = new JTextArea(30, 30);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JCheckBox timePeriod = new JCheckBox("Установите промежуток времени:  ");
        JCheckBox reverse = new JCheckBox("История в обратном порядке");
        JSpinner startDate = TimeSpinner.getSpinner();
        JSpinner endDate = TimeSpinner.getSpinner();
        DefaultListModel<History> listModel = new DefaultListModel<>();
        JList<History> historyList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(historyList);
        JLabel numOfHistoryLabel = new JLabel("Количество записей");

        Container container = this.getContentPane();
        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(infoLabel, BorderLayout.NORTH);

        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints leftPanelConstraints = new GridBagConstraints();
        leftPanelConstraints.fill = GridBagConstraints.HORIZONTAL;

        leftPanelConstraints.gridx = 1;
        leftPanelConstraints.gridy = 0;
        leftPanel.add(translateButton, leftPanelConstraints);

        leftPanelConstraints.gridx = 1;
        leftPanelConstraints.gridy = 1;
        leftPanel.add(updateButton, leftPanelConstraints);

        leftPanelConstraints.gridx = 1;
        leftPanelConstraints.gridy = 2;
        leftPanel.add(openFileButton, leftPanelConstraints);

        leftPanelConstraints.gridheight = 4;
        leftPanelConstraints.gridx = 0;
        leftPanelConstraints.gridy = 0;
        leftPanel.add(cronArea, leftPanelConstraints);

        leftPanelConstraints.gridheight = 4;
        leftPanelConstraints.gridx = 2;
        leftPanelConstraints.gridy = 0;
        leftPanel.add(humanArea, leftPanelConstraints);

        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints rightPanelConstraints = new GridBagConstraints();
        rightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        rightPanelConstraints.gridx = 0;
        rightPanel.add(reverse, rightPanelConstraints);

        rightPanelConstraints.weighty = 0.1;
        rightPanelConstraints.gridx = 0;
        rightPanelConstraints.gridy = 1;
        rightPanel.add(numOfHistoryLabel, rightPanelConstraints);

        rightPanelConstraints.gridx = 1;
        Integer[] num = {10, 20, 30};
        JComboBox<Integer> numOfHistory = new JComboBox<>(num);
        rightPanel.add(numOfHistory, rightPanelConstraints);

        rightPanelConstraints.gridx = 0;
        rightPanelConstraints.gridy = 2;
        rightPanel.add(timePeriod, rightPanelConstraints);

        rightPanelConstraints.gridx = 1;
        rightPanelConstraints.gridy = 2;
        rightPanel.add(startDate, rightPanelConstraints);

        rightPanelConstraints.gridx = 2;
        rightPanelConstraints.gridy = 2;
        rightPanel.add(endDate, rightPanelConstraints);

        rightPanelConstraints.weighty = 0.1;
        rightPanelConstraints.gridwidth = 4;
        rightPanelConstraints.gridx = 0;
        rightPanelConstraints.gridy = 3;
        rightPanel.add(scrollPane, rightPanelConstraints);

        scrollPane.setPreferredSize(new Dimension(600, 400));
        historyList.setLayoutOrientation(JList.VERTICAL);
        humanArea.setLineWrap(true);
        humanArea.setWrapStyleWord(true);

        translateButton.addActionListener(new TranslateButtonListener(cronArea, humanArea));
        updateButton.addActionListener(new UpdateButtonListener(cronArea, humanArea));
        openFileButton.addActionListener(new OpenFileButtonListener(cronArea, humanArea));

        ListListener listListener = new ListListener(cronArea, humanArea, historyList);
        historyList.addListSelectionListener(listListener);
        Timer timer = new Timer(10000, new TimerListener(numOfHistory, listModel, historyList, startDate, endDate, timePeriod, reverse, listListener));
        timer.start();
    }
}