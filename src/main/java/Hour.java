import javax.swing.*;
import java.util.List;

public class Hour extends Phrase {

    public Hour(String[] userInput) {
        super(userInput);
        this.phrase = userInput[1];
    }

    void getPhrase(List<String> fullPhrase) {
//        while (!phrase.matches("[0-9]|[0-5]?[0-9]|\\*")) {
//            JOptionPane.showMessageDialog(null,"Введиете корректное колличество часов(0-59)",
//                    "Warning", JOptionPane.WARNING_MESSAGE);
//        }

        if (phrase.equals("*")) {
            fullPhrase.add("каждый час,");
        } else {
            fullPhrase.add("в " + phrase + " час(ов),");
        }
    }
}