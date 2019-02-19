import java.util.Scanner;

public class Minute extends Phrase {

    public Minute(String[] userInput) {
        super(userInput);
        this.phrase = userInput[0];
    }

    void getPhrase(Scanner scanner) {
        while (!phrase.matches("[0-9]|[0-5]?[0-9]|\\*")){
            System.out.println("\nВведиете корректное колличество минут(0-59): ");
            phrase = scanner.nextLine();
        }

        if (phrase.equals("*")) {
            System.out.print("каждую минуту.");
        } else {
            System.out.print(phrase + " минут(у).");
        }
    }
}
