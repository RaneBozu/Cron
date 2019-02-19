import javax.swing.*;
import java.util.List;

public class DayOfMonth extends Phrase {

    public DayOfMonth(String[] userInput) {
        super(userInput);
        this.phrase = userInput[2];
    }

    void getPhrase(List<String> fullPhrase) {
//        while (!phrase.matches("[0-2]?[0-9]|31|30|\\*") || phrase.equals("0")) {
//            JOptionPane.showMessageDialog(null,"Введиете корректный день месяца(1-31)",
//                    "Warning", JOptionPane.WARNING_MESSAGE);
//        }

        if (phrase.equals("*")) {
            fullPhrase.add("каждый день месяца,");
        } else {
            fullPhrase.add(phrase + " числа,");
        }
    }
}
