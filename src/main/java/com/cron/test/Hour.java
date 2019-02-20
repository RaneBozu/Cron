package com.cron.test;

public class Hour implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[0-9]|[0-1]?[0-9]|20|21|22|23|24|\\*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное колличество часов(0-24)";
    }

    @Override
    public String getPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "час,";
        } else {
            return phrase + " час(ов),";
        }
    }
}