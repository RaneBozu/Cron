package com.cron.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CornGUI extends JFrame {
    private JButton button = new JButton("Check");
    private JTextArea leftArea = new JTextArea(2, 27);
    private JTextArea rightArea = new JTextArea(2, 27);
    private JLabel infoLabel = new JLabel
            ("Введите запрос: параметры разделенные пробелом.(минуты, часы, день месяца, месяц, день недели, сезон)");
    private JLabel infoLabel1 = new JLabel("Выполнять задание");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    CornGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 800, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1.setLayout(new BorderLayout());
        Container container = this.getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new BorderLayout());
        container.add(panel1, BorderLayout.WEST);
        container.add(panel2, BorderLayout.EAST);
        panel1.add(leftArea, BorderLayout.NORTH);
        panel2.add(rightArea, BorderLayout.NORTH);
        container.add(infoLabel, BorderLayout.NORTH);
        container.add(infoLabel1, BorderLayout.CENTER);
        container.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] userInput;
            if(!leftArea.getText().isEmpty()){
                userInput = leftArea.getText().split(" ");
            } else {
                userInput = rightArea.getText().split(" ");
            }

            if (!leftArea.getText().isEmpty() && !rightArea.getText().isEmpty() || userInput.length > 6) {
                JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле, либо введено слишком много параметров",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Phrase[] allPhrases = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
            StringBuilder massage = new StringBuilder("Каждую(ый)");
            for (int i = userInput.length - 1; i >= 0; i--) {
                Phrase phrase = allPhrases[i];
                String input = userInput[i];
                if (phrase.checkValue(input)) {
                    JOptionPane.showMessageDialog(null, phrase.warningMassage(),"Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                massage.append(phrase.getPhrase(input)).append(" ");
            }

            if(!leftArea.getText().isEmpty()){
                rightArea.append(massage.toString());
                leftArea.setText(null);
            } else {
                leftArea.append(massage.toString());
                rightArea.setText(null);
            }
        }
    }
}
