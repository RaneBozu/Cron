package com.alexmail.cron;

class Translator {

    private Phrase[] allPhrase = {new Minute(), new Hour(), new DayOfMonth(), new Month(), new DayOfWeek(), new Season()};
    private String translate = "";
    private String[] userInput;
    private Request request;


    Translator(Request request) {
        this.request = request;
        if (request.isCronMsg()) {
            fromCronToHuman();
        } else {
            fromHumanToCron();
        }
    }

    String getTranslate() {
        return translate;
    }

    /**
     * Translate the message from Cron to Human
     */
    private void fromCronToHuman() {
        userInput = request.getUserInput().split(" ");
        if (userInput.length > 6 || userInput.length < 1) {
            translate = "Введите от 1 до 6 параметров";
            request.setErrorMsg(true);
            return;
        }
        for (int i = 0; i <= userInput.length - 1; i++) {
            Phrase phrase = allPhrase[i];
            String input = userInput[i];
            if (phrase.checkCornValue(input)) {
                request.setErrorMsg(true);
                translate = phrase.warningMassage();
            } else {
                translate += phrase.getHumanPhrase(userInput[i]) + ", ";
            }
        }
    }

    /**
     * Translate the message from Human to Cron
     */
    private void fromHumanToCron() {
        userInput = request.getUserInput().split(", ");
        if (userInput.length > 6 || userInput.length < 1) {
            translate = "Введите от 1 до 6 параметров";
            request.setErrorMsg(true);
            return;
        }
        for (int i = 0; i <= userInput.length - 1; i++) {
            Phrase phrase = allPhrase[i];
            String input = userInput[i];
            if (phrase.checkHumanValue(input)) {
                request.setErrorMsg(true);
                translate = phrase.warningMassage();
            } else {
                translate += allPhrase[i].getCronPhrase(userInput[i]) + " ";
            }
        }
    }
}