import java.util.Scanner;

public class Month {
    String phraseForMonths(String month) {
        while (!month.matches("[1-9]|12|11|10|\\*")) {
            System.out.println("Введиете корректный месяц(1-12): ");
            Scanner scanner = new Scanner(System.in);
            month = scanner.nextLine();
        }

        if (month.equals("*")) {
            return "каждый месяц,";
        }
        switch (month) {
            case "1":
                return "в январе,";
            case "2":
                return "в феврале,";
            case "3":
                return "в марте,";
            case "4":
                return "в апреле,";
            case "5":
                return "в мае,";
            case "6":
                return "в июне,";
            case "7":
                return "в июле,";
            case "8":
                return "в августе,";
            case "9":
                return "в сентябре,";
            case "10":
                return "в октябре,";
            case "11":
                return "в ноябре,";
            case "12":
                return "в декабре,";
            default:
                return "месяц,";
        }
    }
}
