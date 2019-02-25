package com.cron.test;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class CronGUI extends JFrame {
    private JTextArea cronArea = new JTextArea(20, 30);
    private JTextArea humanArea = new JTextArea(20, 30);
    private DefaultListModel<String> dfm = new DefaultListModel<>();
    private JList<String> historyList = new JList<>(dfm);

    CronGUI() throws HeadlessException {
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

        scrollPane.setPreferredSize(new Dimension(300, 400));
        historyList.setLayoutOrientation(JList.VERTICAL);
        humanArea.setLineWrap(true);
        humanArea.setWrapStyleWord(true);

        button.addActionListener(new ButtonListener());
        historyList.addListSelectionListener(new ListListener());
    }

    class ButtonListener implements ActionListener {
        /**
         * Sends a request to translate a message
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Phrase[] allPhrase = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
            String massage = "";
            String[] userInput;
            Translator translator;

            if (!cronArea.getText().isEmpty() && !humanArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!cronArea.getText().isEmpty()) {
                userInput = cronArea.getText().split(" ");
                for (int i = 0; i <= userInput.length - 1; i++) {
                    Phrase phrase = allPhrase[i];
                    if (phrase.checkCornValue(userInput[i])) {
                        JOptionPane.showMessageDialog(null, phrase.warningMassage(), "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                translator = new Translator(userInput, allPhrase);
                translator.setCheckType(true);
            } else {
                userInput = humanArea.getText().split(", ");
                for (int i = 0; i <= userInput.length - 1; i++) {
                    Phrase phrase = allPhrase[i];
                    if (phrase.checkHumanValue(userInput[i])) {
                        JOptionPane.showMessageDialog(null, phrase.warningMassage(), "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                translator = new Translator(userInput, allPhrase);
                translator.setCheckType(false);

            }
            try (Socket socket = new Socket("127.0.0.1", 8050);
                 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream is = new ObjectInputStream(socket.getInputStream())) {

                os.writeObject(translator);
                massage = (String) is.readObject();

            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            if (!cronArea.getText().isEmpty()) {
                humanArea.append(massage);
                dfm.addElement(cronArea.getText() + " -> " + humanArea.getText() + "\n");
                cronArea.setText(null);
            } else {
                cronArea.append(massage);
                dfm.addElement(cronArea.getText() + " <- " + humanArea.getText() + "\n");
                humanArea.setText(null);
            }
        }
    }

    class ListListener implements ListSelectionListener {

        /**
         * Shows the original query
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            String[] value;
            if (historyList.getSelectedValue().contains("->")) {
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