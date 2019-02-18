import java.util.Scanner;

public class DayOfMonth {
    String phraseForDayOfMonth(String dayOfMonth) {
        while (!dayOfMonth.matches("[0-2]?[0-9]|31|30|\\*") || dayOfMonth.equals("0")) {
            System.out.println("Введиете корректный день месяца(1-31): ");
            Scanner scanner = new Scanner(System.in);
            dayOfMonth = scanner.nextLine();
        }
        if (dayOfMonth.equals("*")) {
            return "каждый день месяца,";
        } else {
            return dayOfMonth + " числа,";
        }
    }
}
