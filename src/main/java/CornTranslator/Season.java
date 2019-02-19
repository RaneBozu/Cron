package CornTranslator;

public class Season implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[1-4]|\\*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение сезона";
    }

    @Override
    public String getPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "сезон,";
        }
        switch (phrase) {
            case "1":
                return "Зима,";
            case "2":
                return "Весна,";
            case "3":
                return "Лето,";
            case "4":
                return "Осень,";
            default:
                return "";
        }
    }
}
