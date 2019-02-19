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

        Phrase[] fullPhrase = {new DayOfWeek(userInput),  new Month(userInput),
                new DayOfMonth(userInput), new Hour(userInput), new Minute(userInput)};

        System.out.println("Запускать задание: ");
        for (Phrase phrase : fullPhrase) {
            phrase.getPhrase(scanner);
        }
    }
}