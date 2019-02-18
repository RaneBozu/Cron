import java.util.Scanner;

public class Cron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите запрос: 5 параметров разделенные пробелом.(минуты, часы, день месяца, месяц, день недели)");
        String[] userInput = scanner.nextLine().split(" ");
        while(userInput.length != 5) {
            System.out.println("Введите корректный запрос (необходимо ввести 5 параметров)");
            userInput = scanner.nextLine().split(" ");
        }
        Minute minute = new Minute();
        Hour hour = new Hour();
        DayOfMonth dayOfMonth = new DayOfMonth();
        DayOfWeek dayOfWeek = new DayOfWeek();
        Month month = new Month();
        String[] period = {minute.phraseForMinutes(userInput[0]), hour.phraseForHours(userInput[1]),
                dayOfMonth.phraseForDayOfMonth(userInput[2]), month.phraseForMonths(userInput[3]),
                dayOfWeek.phraseForDayOfTheWeek(userInput[4])};
        System.out.println("Запускать задание: ");
        for (int i = userInput.length - 1; i >= 0; i--) {
            System.out.print(period[i] + " ");
        }
    }
}
