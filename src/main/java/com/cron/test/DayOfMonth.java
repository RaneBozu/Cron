package com.cron.test;

import java.io.Serializable;

public class DayOfMonth implements Phrase, Serializable {

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[0-2]?[0-9]|31|30|\\*") || phrase.equals("0");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        return !phrase.matches(".*кажд.*|.*[0-2]?[0-9].*числа.*|.*31.*числа.*|.*30.*числа.*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"день месяца\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждый день месяца";
        } else {
            return phrase + " числа";
        }
    }

    @Override
    public String getCronPhrase(String phrase) {
        if (phrase.matches(".*каждый.*")) {
            return Cron.EVERY_PERIOD_OF_TIME;
        } else {
            phrase = phrase.trim();
            return phrase.replaceAll("\\D+", "");
        }
    }
}