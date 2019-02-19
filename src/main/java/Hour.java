import java.util.Scanner;

public class Hour extends Phrase {

    public Hour(String[] userInput) {
        super(userInput);
        this.phrase = userInput[1];
    }

    void getPhrase(Scanner scanner) {
        while (!phrase.matches("[0-9]|[0-5]?[0-9]|\\*")) {
            System.out.println("\nВведиете корректное колличество часов(0-59): ");
            phrase = scanner.nextLine();
        }

        if (phrase.equals("*")) {
            System.out.print("каждый час,");
        } else {
            System.out.print("в " + phrase + " час(ов),");
        }
    }
}