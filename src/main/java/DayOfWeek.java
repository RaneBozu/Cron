import java.util.Scanner;

public class DayOfWeek {
    String phraseForDayOfTheWeek(String dayOfTheWeek) {
        while (!dayOfTheWeek.matches("[1-7]|\\*")) {
            System.out.println("Введиете корректный день недели(1-7): ");
            Scanner scanner = new Scanner(System.in);
            dayOfTheWeek = scanner.nextLine();
        }

        if (dayOfTheWeek.equals("*")) {
            return "каждый день недели,";
        }
        switch (dayOfTheWeek) {
            case "1":
                return "каждый Понедельник,";
            case "2":
                return "каждый Вторник,";
            case "3":
                return "каждыю Среду,";
            case "4":
                return "каждый Четверг,";
            case "5":
                return "каждую Пятницу,";
            case "6":
                return "каждую Субботу,";
            case "7":
                return "каждое Воскресенье,";
            default:
                return "день недели,";
        }
    }
}
