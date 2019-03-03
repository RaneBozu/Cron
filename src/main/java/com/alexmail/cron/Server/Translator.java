package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.Response;

class Translator {

    private Phrase[] phrases = {Phrase.MINUTE, Phrase.HOUR, Phrase.DAY_OF_MONTH, Phrase.MONTH, Phrase.DAY_OF_WEEK, Phrase.SEASON};
    private String[] userInput;
    private Request request;
    private Response response;

    Translator(Request request, Response response) {
        this.request = request;
        this.response = response;

        if (request.isCronMsg()) {
            fromCronToHuman();
        } else {
            fromHumanToCron();
        }
    }

    Response getResponse() {
        return response;
    }

    /**
     * Translate the message from Cron to Human
     */
    private void fromCronToHuman() {

        StringBuilder result = new StringBuilder();
        userInput = request.getInputMsg().split(" ");
        if (userInput.length > 6 || userInput.length < 1) {
            response.setErrorMsg("Введите от 1 до 6 параметров");
            return;
        }

        for (int i = 0; i <= userInput.length - 1; i++) {

            Phrase phrase = phrases[i];
            String input = userInput[i];
            if (phrase.checkCornValue(input)) {
                response.setErrorMsg(phrase.warningMassage());
            } else {
                result.append(phrase.getHumanPhrase(userInput[i])).append(", ");
            }
        }
        response.setOutputMsg(result.toString());
    }

    /**
     * Translate the message from Human to Cron
     */
    private void fromHumanToCron() {

        StringBuilder result = new StringBuilder();
        userInput = request.getInputMsg().split(", ");
        if (userInput.length > 6 || userInput.length < 1) {
            response.setErrorMsg("Введите от 1 до 6 параметров");
            return;
        }
        for (int i = 0; i <= userInput.length - 1; i++) {
            Phrase phrase = phrases[i];
            String input = userInput[i];
            if (phrase.checkHumanValue(input)) {
                response.setErrorMsg(phrase.warningMassage());
            } else {
                result.append(phrase.getCronPhrase(userInput[i])).append(" ");
            }
        }
        response.setOutputMsg(result.toString());
    }
}