import javax.swing.*;
import java.util.List;

public class Minute extends Phrase {

    public Minute(String[] userInput) {
        super(userInput);
        this.phrase = userInput[0];
    }

    void getPhrase(List<String> fullPhrase) {
//        while (!phrase.matches("[0-9]|[0-5]?[0-9]|\\*")){
//            JOptionPane.showMessageDialog(null,"Введиете корректное колличество минут(0-59)",
//                    "Warning", JOptionPane.WARNING_MESSAGE);
//        }

        if (phrase.equals("*")) {
            fullPhrase.add("каждую минуту.");
        } else {
            fullPhrase.add(phrase + " минут(у).");
        }
    }
}
