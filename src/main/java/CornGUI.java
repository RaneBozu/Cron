import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class CornGUI extends JFrame {
    JButton button = new JButton("Check");
    JTextArea textArea = new JTextArea("введите запрос",20, 20);
    JLabel infoLable = new JLabel("Введите запрос: 5 параметров разделенные пробелом.(минуты, часы, день месяца, месяц, день недели)");

    public CornGUI(List<String> fullPhrase) throws HeadlessException {
        super("Corn");
        this.setBounds(200, 200, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setBackground(Color.gray);
        container.setLayout(new BorderLayout());
        container.add(textArea, BorderLayout.WEST);
        container.add(button, BorderLayout.SOUTH);
        container.add(infoLable, BorderLayout.NORTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] userInput = textArea.getText().split(" ");
                while(userInput.length != 5) {
                    JOptionPane.showMessageDialog(null, "Введите корректный запрос (необходимо ввести 5 параметров)",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Phrase[] ph = {new DayOfWeek(userInput),  new Month(userInput),
                        new DayOfMonth(userInput), new Hour(userInput), new Minute(userInput)};
                String massage = "Запускать задание: ";
                for (Phrase phrase : ph) {
                    phrase.getPhrase(fullPhrase);
                }
                for (String str : fullPhrase) {
                    massage += str + " ";
                }
                JOptionPane.showMessageDialog(null, massage, "Translate", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
