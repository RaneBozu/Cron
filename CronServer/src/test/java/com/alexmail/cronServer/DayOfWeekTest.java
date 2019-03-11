package com.alexmail.cronServer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DayOfWeekTest {

    private Phrase dayOfWeek;
    private String cronMsg;
    private String humanMsg;

    @Before
    public void setUp() throws Exception {
        dayOfWeek = Phrase.DAY_OF_WEEK;
        cronMsg = "1";
        humanMsg = "Понедельник";
    }

    @Test
    public void checkCornValue() {
        boolean actual = dayOfWeek.checkCornValue(cronMsg);
        assertFalse(actual);
    }

    @Test
    public void checkHumanValue() {
        boolean actual = dayOfWeek.checkHumanValue(humanMsg);
        assertFalse(actual);
    }

    @Test
    public void getHumanPhrase() {
        String expected = humanMsg;
        String actual = dayOfWeek.getHumanPhrase(cronMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getCronPhrase() {
        String expected = cronMsg;
        String actual = dayOfWeek.getCronPhrase(humanMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}