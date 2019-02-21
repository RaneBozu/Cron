package com.cron.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Month implements Phrase {

    private List<String> months = new ArrayList<>(Arrays.asList("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октабрь", "Ноябрь", "Декабрь"));

    @Override
    public boolean checkCornValue(String phrase) {
        return !phrase.matches("[1-9]|12|11|10|\\*");
    }

    @Override
    public boolean checkHumanValue(String phrase) {
        for (String dayOfWeek : months) {
            if (phrase.contains(dayOfWeek)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String warningMassage() {
        return "Введиете корректное значение для поля \"месяц\"";
    }

    @Override
    public String getHumanPhrase(String phrase) {
        if (phrase.equals(Cron.EVERY_PERIOD_OF_TIME)) {
            return "каждый месяц";
        } else {
            int temp = Integer.parseInt(phrase);
            return months.get(temp - 1);
        }
    }

    @Override
    public String getCronPhrase(String phrase) {
        if (phrase.matches(".*каждый.*")) {
            return Cron.EVERY_PERIOD_OF_TIME;
        } else {
            for (int i = 0; i < months.size(); i++) {
                if (phrase.contains(months.get(i))) {
                    return Integer.toString(i + 1);
                }
            }
        }
        return "";
    }
}


