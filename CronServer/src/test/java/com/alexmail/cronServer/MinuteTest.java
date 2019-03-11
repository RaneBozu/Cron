package com.alexmail.cronServer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class MinuteTest {

    private Phrase minute;
    private String cronMsg;
    private String humanMsg;

    @Before
    public void setUp() throws Exception {
        minute = Phrase.MINUTE;
        cronMsg = "1";
        humanMsg = "1 минут(у)";
    }

    @Test
    public void checkCornValue() {
        boolean actual = minute.checkCornValue(cronMsg);
        assertFalse(actual);
    }

    @Test
    public void checkHumanValue() {
        boolean actual = minute.checkHumanValue(humanMsg);
        assertFalse(actual);
    }

    @Test
    public void getHumanPhrase() {
        String expected = humanMsg;
        String actual = minute.getHumanPhrase(cronMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getCronPhrase() {
        String expected = cronMsg;
        String actual = minute.getCronPhrase(humanMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}