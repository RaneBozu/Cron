package com.alexmail.cron.Server.Phrases;

public interface Phrases {




    /**
     * Checking the validity of the Cron request
     * */
    boolean checkCornValue(String phrase);

    /**
     * Checking the validity of the human request
     * */
    boolean checkHumanValue(String phrase);

    /**
     * Returns an error message
     * */
    String warningMassage();

    /**
     * Returns a human message
     * */
    String getHumanPhrase(String phrase);

    /**
     * Returns a Cron message
     * */
    String getCronPhrase(String phrase);
}