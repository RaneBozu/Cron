import java.util.Scanner;

public class Month extends Phrase {
    public Month(String[] userInput) {
        super(userInput);
        this.phrase = userInput[3];
    }

    void getPhrase(Scanner scanner) {

        while (!phrase.matches("[1-9]|12|11|10|\\*")) {
            System.out.println("\nВведиете корректный месяц(1-12): ");
            phrase = scanner.nextLine();
        }

        if (phrase.equals("*")) {
            System.out.print("каждый месяц,");
        }
        switch (phrase) {
            case "1":
                System.out.print("в январе,");
                break;
            case "2":
                System.out.print("в феврале,");
                break;
            case "3":
                System.out.print("в марте,");
                break;
            case "4":
                System.out.print("в апреле,");
                break;
            case "5":
                System.out.print("в мае,");
                break;
            case "6":
                System.out.print("в июне,");
                break;
            case "7":
                System.out.print("в июле,");
                break;
            case "8":
                System.out.print("в августе,");
                break;
            case "9":
                System.out.print("в сентябре,");
                break;
            case "10":
                System.out.print("в октябре,");
                break;
            case "11":
                System.out.print("в ноябре,");
                break;
            case "12":
                System.out.print("в декабре,");
                break;
        }
    }
}


