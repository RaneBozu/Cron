import javax.swing.*;
import java.util.List;

public class Month extends Phrase {

    public Month(String[] userInput) {
        super(userInput);
        this.phrase = userInput[3];
    }

    void getPhrase(List<String> fullPhrase) {
//        while (!phrase.matches("[1-9]|12|11|10|\\*")) {
//            JOptionPane.showMessageDialog(null,"Введиете корректный месяц(1-12)",
//                    "Warning", JOptionPane.WARNING_MESSAGE);
//        }

        if (phrase.equals("*")) {
            fullPhrase.add("каждый месяц,");
        }
        switch (phrase) {
            case "1":
                fullPhrase.add("в январе,");
                break;
            case "2":
                fullPhrase.add("в феврале,");
                break;
            case "3":
                fullPhrase.add("в марте,");
                break;
            case "4":
                fullPhrase.add("в апреле,");
                break;
            case "5":
                fullPhrase.add("в мае,");
                break;
            case "6":
                fullPhrase.add("в июне,");
                break;
            case "7":
                fullPhrase.add("в июле,");
                break;
            case "8":
                fullPhrase.add("в августе,");
                break;
            case "9":
                fullPhrase.add("в сентябре,");
                break;
            case "10":
                fullPhrase.add("в октябре,");
                break;
            case "11":
                fullPhrase.add("в ноябре,");
                break;
            case "12":
                fullPhrase.add("в декабре,");
                break;
        }
    }
}


