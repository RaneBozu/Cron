package com.alexmail.cronServer;

import java.util.Arrays;
import java.util.List;

public enum Phrase {

    MINUTE {
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
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждую минуту";
            } else {
                return phrase + " минут(у)";
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждую.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                phrase = phrase.trim();
                return phrase.replaceAll("\\D+", "");
            }
        }
    },

    HOUR {
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
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждый час";
            } else {
                return phrase + " час(ов)";
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждый.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                phrase = phrase.trim();
                return phrase.replaceAll("\\D+", "");
            }
        }
    },

    DAY_OF_MONTH {
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
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждый день месяца";
            } else {
                return phrase + " числа";
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждый.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                phrase = phrase.trim();
                return phrase.replaceAll("\\D+", "");
            }
        }
    },

    MONTH {
        private List<String> months = Arrays.asList("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октабрь", "Ноябрь", "Декабрь");

        @Override
        public boolean checkCornValue(String phrase) {
            return !phrase.matches("[1-9]|12|11|10|\\*");
        }

        @Override
        public boolean checkHumanValue(String phrase) {
            return !months.contains(phrase);
        }

        @Override
        public String warningMassage() {
            return "Введиете корректное значение для поля \"месяц\"";
        }

        @Override
        public String getHumanPhrase(String phrase) {
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждый месяц";
            } else {
                int temp = Integer.parseInt(phrase);
                return months.get(temp - 1);
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждый.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                return Integer.toString(months.indexOf(phrase) + 1);
            }
        }
    },

    SEASON {
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
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждый сезон";
            } else {
                int temp = Integer.parseInt(phrase);
                return seasons.get(temp - 1);
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждый.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                return Integer.toString(seasons.indexOf(phrase) + 1);
            }
        }
    },

    DAY_OF_WEEK {
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
            if (phrase.equals(EVERY_PERIOD_OF_TIME)) {
                return "каждый день недели";
            } else {
                int temp = Integer.parseInt(phrase);
                return daysOfWeek.get(temp - 1);
            }
        }

        @Override
        public String getCronPhrase(String phrase) {
            if (phrase.matches(".*каждый.*")) {
                return EVERY_PERIOD_OF_TIME;
            } else {
                return Integer.toString(daysOfWeek.indexOf(phrase) + 1);
            }
        }
    };

    public final static String EVERY_PERIOD_OF_TIME = "*";
    /**
     * Checking the validity of the Cron request
     */
    public abstract boolean checkCornValue(String phrase);

    /**
     * Checking the validity of the human request
     */
    abstract boolean checkHumanValue(String phrase);

    /**
     * Returns an error message
     */
    abstract String warningMassage();

    /**
     * Returns a human message
     */
    abstract String getHumanPhrase(String phrase);

    /**
     * Returns a Cron message
     */
    abstract String getCronPhrase(String phrase);
}
