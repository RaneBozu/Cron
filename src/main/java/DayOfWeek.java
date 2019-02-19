import javax.swing.*;
import java.util.List;

public class DayOfWeek extends Phrase {

    public DayOfWeek(String[] userInput) {
        super(userInput);
        this.phrase = userInput[4];
    }

    void getPhrase(List<String> fullPhrase) {
//        while (!phrase.matches("[1-7]|\\*")) {
//            JOptionPane.showMessageDialog(null,"Введиете корректный день недели(1-7)",
//                    "Warning", JOptionPane.WARNING_MESSAGE);
//        }

        if (phrase.equals("*")) {
            fullPhrase.add("каждый день недели,");
        }
        switch (phrase) {
            case "1":
                fullPhrase.add("каждый Понедельник,");
                break;
            case "2":
                fullPhrase.add("каждый Вторник,");
                break;
            case "3":
                fullPhrase.add("каждыю Среду,");
                break;
            case "4":
                fullPhrase.add("каждый Четверг,");
                break;
            case "5":
                fullPhrase.add("каждую Пятницу,");
                break;
            case "6":
                fullPhrase.add("каждую Субботу,");
                break;
            case "7":
                fullPhrase.add("каждое Воскресенье,");
                break;
        }
    }
}
