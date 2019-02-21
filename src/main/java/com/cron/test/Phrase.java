package com.cron.test;

public interface Phrase {
    boolean checkCornValue(String phrase);
    boolean checkHumanValue(String phrase);
    String warningMassage();
    String getHumanPhrase(String phrase);
    String getCronPhrase(String phrase);
}
