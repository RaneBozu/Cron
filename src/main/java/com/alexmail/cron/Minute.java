package com.alexmail.cron;

import java.io.Serializable;

public class Minute implements Phrase, Serializable {

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[0-9]|[0-5]?[0-9]|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        return !phrase.matches(".*кажд.*|.*[0-9].*мин.*|.*[0-5]?[0-9].*мин.*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"минуты\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждую минуту";
        } else {
            return phrase + " минут(у)";
        }
    }

    @Override
    public String getCronPhrase(String phrase) {
        if (phrase.matches(".*каждую.*")) {
            return Cron.EVERY_PERIOD_OF_TIME;
        } else {
            phrase = phrase.trim();
            return phrase.replaceAll("\\D+", "");
        }
    }
}