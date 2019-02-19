package CornTranslator;

public class Month implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[1-9]|12|11|10|\\*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректный месяц(1-12)";
    }

    public String getPhrase(String phrase) {

        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "месяц,";
        }
        switch (phrase) {
            case "1":
                return "январь,";
            case "2":
                return "февраль,";
            case "3":
                return "март,";
            case "4":
                return "апрель,";
            case "5":
                return "май,";
            case "6":
                return "июнь,";
            case "7":
                return "июль,";
            case "8":
                return "август,";
            case "9":
                return "сентябрь,";
            case "10":
                return "октабрь,";
            case "11":
                return "ноябрь,";
            case "12":
                return "декабрь,";
            default:
                return "";
        }
    }
}


