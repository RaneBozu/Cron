package com.cron.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Season implements Phrase {

    private List<String> seasons = new ArrayList<>(Arrays.asList("Зима", "Весна", "Лето", "Осень"));

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[1-4]|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        for (String season : seasons) {
            if (phrase.contains(season)) {
                return false;
            }
        }
        return true;
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
            for (int i = 0; i < seasons.size(); i++) {
                if (phrase.contains(seasons.get(i))) {
                    return Integer.toString(i + 1);
                }
            }
        }
        return "";
    }
}
