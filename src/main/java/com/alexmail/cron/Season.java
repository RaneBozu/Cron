package com.alexmail.cron;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Season implements Phrase, Serializable {

    private List<String> seasons = Arrays.asList("Зима", "Весна", "Лето", "Осень");

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[1-4]|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        return !seasons.contains(phrase);
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"сезон\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждый сезон";
        } else {
            int temp = Integer.parseInt(phrase);
            return seasons.get(temp - 1);
        }
    }

    @Override
    public String getCronPhrase(String phrase) {
        if (phrase.matches(".*каждый.*")) {
            return Cron.EVERY_PERIOD_OF_TIME;
        } else {
            return Integer.toString(seasons.indexOf(phrase) + 1);
        }
    }
}