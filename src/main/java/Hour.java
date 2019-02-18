import java.util.Scanner;

public class Hour {
    String phraseForHours(String hours) {
        if (!hours.matches("[0-5]?[0-9]|\\*")) {
            System.out.println("Введиете корректное колличество часов: ");
            Scanner scanner = new Scanner(System.in);
            hours = scanner.nextLine();
            phraseForHours(hours);
        }
        if (hours.equals("*")) {
            return "каждый час,";
        } else {
            return "в " + hours + " час(ов),";
        }

    }
}
