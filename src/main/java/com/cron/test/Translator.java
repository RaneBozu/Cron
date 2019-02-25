package com.cron.test;

import java.io.Serializable;

class Translator implements Serializable {

    private Phrase[] allPhrase;
    private String[] userInput;
    private boolean checkType;

    Translator(String[] userInput, Phrase[] allPhrase) {
        this.userInput = userInput;
        this.allPhrase = allPhrase;
    }

    void setCheckType(boolean checkType) {
        this.checkType = checkType;
    }

    /**
     * Translates the request between Cron and human language
     * */
    String getTranslate() {

        StringBuilder message = new StringBuilder();
        if(checkType) {
            for (int i = 0; i <= userInput.length - 1; i++) {
                message.append(allPhrase[i].getHumanPhrase(userInput[i])).append(", ");
            }
            return message.toString();
        } else {
            for (int i = 0; i <= userInput.length - 1; i++) {
                message.append(allPhrase[i].getCronPhrase(userInput[i])).append(" ");
            }
            return message.toString();
        }
    }
}