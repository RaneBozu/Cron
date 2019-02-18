import java.util.Scanner;

public class Minute {
    String phraseForMinutes(String minute) {
        while (!minute.matches("[0-9]|[0-5]?[0-9]|\\*")) {
            System.out.println("Введиете корректное колличество минут(0-59): ");
            Scanner scanner = new Scanner(System.in);
            minute = scanner.nextLine();
        }

        if (minute.equals("*")) {
            return "каждую минуту.";
        } else {
            return minute + " минут(у).";
        }
    }
}
