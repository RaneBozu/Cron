package com.alexmail.cronServer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class HourTest {

    private Phrase hours;
    private String cronMsg;
    private String humanMsg;

    @Before
    public void setUp() throws Exception {
        hours = Phrase.HOUR;
        cronMsg = "1";
        humanMsg = "1 час(ов)";
    }

    @Test
    public void checkCornValue() {
        boolean actual = hours.checkCornValue(cronMsg);
        assertFalse(actual);
    }

    @Test
    public void checkHumanValue() {
        boolean actual = hours.checkHumanValue(humanMsg);
        assertFalse(actual);
    }

    @Test
    public void getHumanPhrase() {
        String expected = humanMsg;
        String actual = hours.getHumanPhrase(cronMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getCronPhrase() {
        String expected = cronMsg;
        String actual = hours.getCronPhrase(humanMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}