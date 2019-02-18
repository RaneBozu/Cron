import java.util.Scanner;

public class DayOfMonth {
    String phraseForDayOfMonth(String dayOfMonth) {

        if (!dayOfMonth.matches("[0-2]?[1-9]|31|30|\\*")) {
            System.out.println("Введиете корректный день месяца: ");
            Scanner scanner = new Scanner(System.in);
            dayOfMonth = scanner.nextLine();
            phraseForDayOfMonth(dayOfMonth);
        }
        if (dayOfMonth.equals("*")) {
            return "каждый день месяца,";
        } else {
            return dayOfMonth + " числа,";
        }
    }
}
