package CornTranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

class CornGUI extends JFrame {
    private JButton button = new JButton("Check");
    private JTextArea leftArea = new JTextArea(20, 20);
    private JTextArea rightArea = new JTextArea(20, 20);
    private JLabel infoLabel = new JLabel
            ("Введите запрос: параметры разделенные пробелом.(минуты, часы, день месяца, месяц, день недели, сезон)");
    private JLabel infoLabel1 = new JLabel("Выполнять задание");

    CornGUI() throws HeadlessException {
        super("Corn");

        this.setBounds(200, 200, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setBackground(Color.gray);
        container.setLayout(new BorderLayout());
        container.add(leftArea, BorderLayout.WEST);
        container.add(rightArea, BorderLayout.EAST);
        container.add(infoLabel, BorderLayout.NORTH);
        container.add(infoLabel1, BorderLayout.CENTER);
        container.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<String > userInput;
            if(!leftArea.getText().isEmpty()){
                userInput = Arrays.asList(leftArea.getText().split(" "));
            } else {
                userInput = Arrays.asList(rightArea.getText().split(" "));
            }

            if (!leftArea.getText().isEmpty() && !rightArea.getText().isEmpty() || userInput.size() > 6) {
                JOptionPane.showMessageDialog(null, "Возможен ввод только в одно поле, либо введено слишком много параметров",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Phrase[] ph = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
            StringBuilder massage = new StringBuilder("Каждый(ые) ");
            for (int i = userInput.size() - 1; i >= 0; i--) {
                if (ph[i].checkValue(userInput.get(i))) {
                    JOptionPane.showMessageDialog(null, ph[i].warningMassage(),"Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                massage.append(ph[i].getPhrase(userInput.get(i))).append(" ");
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
