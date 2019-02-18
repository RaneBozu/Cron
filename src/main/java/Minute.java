import java.util.Scanner;

public class Minute {
    String phraseForMinutes(String minute) {
        if (!minute.matches("[0-5]?[0-9]|\\*")) {
            System.out.println("Введиете корректное колличество минут: ");
            Scanner scanner = new Scanner(System.in);
            minute = scanner.nextLine();
            phraseForMinutes(minute);
        }
        if (minute.equals("*")) {
            return "каждую минуту,";
        } else {
            return minute + " минут(у).";
        }
    }
}
