import java.util.Scanner;

public class Hour {
    String phraseForHours(String hours) {
        while (!hours.matches("[0-9]|[0-5]?[0-9]|\\*")) {
            System.out.println("Введиете корректное колличество часов(0-59): ");
            Scanner scanner = new Scanner(System.in);
            hours = scanner.nextLine();
        }

        if (hours.equals("*")) {
            return "каждый час,";
        } else {
            return "в " + hours + " час(ов),";
        }

    }
}
