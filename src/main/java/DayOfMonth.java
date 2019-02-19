import java.util.Scanner;

public class DayOfMonth extends Phrase {

    public DayOfMonth(String[] userInput) {
        super(userInput);
        this.phrase = userInput[2];
    }

    void getPhrase(Scanner scanner) {

        while (!phrase.matches("[0-2]?[0-9]|31|30|\\*") || phrase.equals("0")) {
            System.out.println("\nВведиете корректный день месяца(1-31): ");
            phrase = scanner.nextLine();
        }

        if (phrase.equals("*")) {
            System.out.print("каждый день месяца,");
        } else {
            System.out.print(phrase + " числа,");
        }
    }
}
