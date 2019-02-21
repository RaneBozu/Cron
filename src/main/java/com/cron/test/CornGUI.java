package com.cron.test;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CornGUI extends JFrame {
    private JTextArea cronArea = new JTextArea(20, 30);
    private JTextArea humanArea = new JTextArea(20, 30);
    private DefaultListModel<String> dfm = new DefaultListModel<>();
    private JList<String> historyList = new JList<>(dfm);

    CornGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 1100, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel leftPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(historyList);
        JLabel infoLabel = new JLabel
                ("Введите запрос: параметры разделенные пробелом.(минуты, часы, день месяца, месяц, день недели, сезон)");

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
        JButton button = new JButton("Check");
        buttonPanel.add(button, BorderLayout.WEST);

        scrollPane.setPreferredSize(new Dimension(300,400));
        historyList.setLayoutOrientation(JList.VERTICAL);
        humanArea.setLineWrap(true);
        humanArea.setWrapStyleWord(true);

        button.addActionListener(new ButtonListener());
        historyList.addListSelectionListener(new ListListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Phrase[] allPhrase = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
            StringBuilder massage = new StringBuilder();
            String[] userInput;

            if (!cronArea.getText().isEmpty() && !humanArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!cronArea.getText().isEmpty()) {
                userInput = cronArea.getText().split(" ");
                for (int i = 0; i <= userInput.length - 1; i++) {
                    Phrase phrase = allPhrase[i];
                    String input = userInput[i];
                    if (phrase.checkCornValue(input)) {
                        JOptionPane.showMessageDialog(null, phrase.warningMassage(), "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    massage.append(phrase.getHumanPhrase(input)).append(", ");
                }
            } else {
                userInput = humanArea.getText().split(", ");
                for (int i = 0; i <= userInput.length - 1; i++) {
                    Phrase phrase = allPhrase[i];
                    String input = userInput[i];
                    if (phrase.checkHumanValue(input)) {
                        JOptionPane.showMessageDialog(null, phrase.warningMassage(), "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    massage.append(phrase.getCronPhrase(input)).append(" ");
                }
            }

            if (!cronArea.getText().isEmpty()) {
                humanArea.append(massage.toString());
                dfm.addElement(cronArea.getText() + " -> " + humanArea.getText() + "\n");
                cronArea.setText(null);
            } else {
                cronArea.append(massage.toString());
                dfm.addElement(cronArea.getText() + " <- " + humanArea.getText() + "\n");
                humanArea.setText(null);
            }
        }
    }
    class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String[] value;
            if(historyList.getSelectedValue().contains("->")) {
                value = historyList.getSelectedValue().split("->");
                cronArea.setText(value[0]);
                humanArea.setText(value[1]);
            } else {
                value = historyList.getSelectedValue().split("<-");
                cronArea.setText(value[0]);
                humanArea.setText(value[1]);
            }
        }
    }
}
