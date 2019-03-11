package com.alexmail.cronServer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SeasonTest {

    private Phrase season;
    private String cronMsg;
    private String humanMsg;

    @Before
    public void setUp() throws Exception {
        season = Phrase.SEASON;
        cronMsg = "1";
        humanMsg = "Зима";
    }

    @Test
    public void checkCornValue() {
        boolean actual = season.checkCornValue(cronMsg);
        assertFalse(actual);
    }

    @Test
    public void checkHumanValue() {
        boolean actual = season.checkHumanValue(humanMsg);
        assertFalse(actual);
    }

    @Test
    public void getHumanPhrase() {
        String expected = humanMsg;
        String actual = season.getHumanPhrase(cronMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getCronPhrase() {
        String expected = cronMsg;
        String actual = season.getCronPhrase(humanMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}