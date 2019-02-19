import java.util.Scanner;

public class DayOfWeek extends Phrase {
    public DayOfWeek(String[] userInput) {
        super(userInput);
        this.phrase = userInput[4];
    }

    void getPhrase(Scanner scanner) {

        while (!phrase.matches("[1-7]|\\*")) {
            System.out.println("\nВведиете корректный день недели(1-7): ");
            phrase = scanner.nextLine();
        }

        if (phrase.equals("*")) {
            System.out.print("каждый день недели,");
        }
        switch (phrase) {
            case "1":
                System.out.print("каждый Понедельник,");
                break;
            case "2":
                System.out.print("каждый Вторник,");
                break;
            case "3":
                System.out.print("каждыю Среду,");
                break;
            case "4":
                System.out.print("каждый Четверг,");
                break;
            case "5":
                System.out.print("каждую Пятницу,");
                break;
            case "6":
                System.out.print("каждую Субботу,");
                break;
            case "7":
                System.out.print("каждое Воскресенье,");
                break;
        }
    }
}
