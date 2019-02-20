package com.cron.test;

public class DayOfWeek implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[1-7]|\\*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректный день недели(1-7)";
    }

    @Override
    public String getPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "день недели,";
        }
        switch (phrase) {
            case "1":
                return "Понедельник,";
            case "2":
                return "Вторник,";
            case "3":
                return "Среда,";
            case "4":
                return "Четверг,";
            case "5":
                return "Пятница,";
            case "6":
                return "Суббота,";
            case "7":
                return "Воскресенье,";
            default:
                return "";
        }
    }
}
