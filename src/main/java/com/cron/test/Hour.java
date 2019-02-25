package com.cron.test;

import java.io.Serializable;

public class Hour implements Phrase, Serializable {

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[0-9]|[0-1]?[0-9]|20|21|22|23|24|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        return !phrase.matches(".*кажд.*|.*[0-9].*час.*|.*[0-1]?[0-9].*час.*|.*20.*час.*|.*21.*час.*|.*22.*час.*|.*23.*час.*|.*24.*час.*");
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"часы\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждый час";
        } else {
            return phrase + " час(ов)";
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