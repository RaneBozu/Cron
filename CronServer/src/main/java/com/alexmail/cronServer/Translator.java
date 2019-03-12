package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

class Translator {

    private Phrase[] phrases = {Phrase.MINUTE, Phrase.HOUR, Phrase.DAY_OF_MONTH, Phrase.MONTH, Phrase.DAY_OF_WEEK, Phrase.SEASON};
    private String[] userInput;
    private Request request;

    Translator(Request request) {
        this.request = request;
    }

    Response getResponse() {
        return request.isCronMsg() ? fromCronToHuman() : fromHumanToCron();
    }

    /**
     * Translate the message from Cron to Human
     */
    private Response fromCronToHuman() {

        Response response = new Response();
        StringBuilder result = new StringBuilder();
        userInput = request.getInputMsg().split(" ");
        if (userInput.length > 6 || userInput.length < 1) {
            response.setErrorMsg("Введите от 1 до 6 параметров");
            return response;
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
        return response;
    }

    /**
     * Translate the message from Human to Cron
     */
    private Response fromHumanToCron() {

        Response response = new Response();
        StringBuilder result = new StringBuilder();
        userInput = request.getInputMsg().split(", ");
        if (userInput.length > 6 || userInput.length < 1) {
            response.setErrorMsg("Введите от 1 до 6 параметров");
            return response;
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
        return response;
    }
}