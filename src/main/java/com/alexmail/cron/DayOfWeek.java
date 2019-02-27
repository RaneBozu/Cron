package com.alexmail.cron;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DayOfWeek implements Phrase, Serializable {

    private List<String> daysOfWeek = Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье");

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[1-7]|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        return !daysOfWeek.contains(phrase);
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"день недели\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждый день недели";
        } else {
            int temp = Integer.parseInt(phrase);
            return daysOfWeek.get(temp - 1);
        }
    }

    @Override
    public String getCronPhrase(String phrase) {
        if (phrase.matches(".*каждый.*")) {
            return Cron.EVERY_PERIOD_OF_TIME;
        } else {
            return Integer.toString(daysOfWeek.indexOf(phrase) + 1);
        }
    }
}