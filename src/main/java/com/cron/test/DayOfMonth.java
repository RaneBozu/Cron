package com.cron.test;

public class DayOfMonth implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[0-2]?[0-9]|31|30|\\*") || phrase.equals("0");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректный день месяца(1-31)";
    }

    @Override
    public String getPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "день месяца,";
        } else {
            return phrase + " числа,";
        }
    }
}