package com.cron.test;

public class Minute implements Phrase {

    @Override
    public boolean checkValue(String phrase) {
        return !phrase.matches("[0-9]|[0-5]?[0-9]|\\*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное колличество минут(0-59)";
    }

    @Override
    public String getPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return " минуту.";
        } else {
            return phrase + " минут(у).";
        }
    }
}