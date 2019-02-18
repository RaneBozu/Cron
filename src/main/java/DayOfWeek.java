import java.util.Scanner;

public class DayOfWeek {
    String phraseForDayOfTheWeek(String dayOfTheWeek) {
        if (dayOfTheWeek.equals("*")) {
            return "каждый день недели,";
        }
        if (!dayOfTheWeek.matches("[1-7]|\\*")) {
            System.out.println("Введиете корректный день недели: ");
            Scanner scanner = new Scanner(System.in);
            dayOfTheWeek = scanner.nextLine();
            phraseForDayOfTheWeek(dayOfTheWeek);
        }
        switch (dayOfTheWeek) {
            case "1":
                return "каждый Понедельник";
            case "2":
                return "каждый Вторник";
            case "3":
                return "каждыю Среду";
            case "4":
                return "каждый Четверг";
            case "5":
                return "каждую Пятницу";
            case "6":
                return "каждую Субботу";
            case "7":
                return "каждое Воскресенье";
            default:
                return "день недели,";
        }
    }
}
