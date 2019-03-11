package com.alexmail.cronServer;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DayOfMonthTest {

    private Phrase dayOfMonth;
    private String cronMsg;
    private String humanMsg;

    @Before
    public void setUp() throws Exception {
        dayOfMonth = Phrase.DAY_OF_MONTH;
        cronMsg = "1";
        humanMsg = "1 числа";
    }

    @Test
    public void checkCornValue() {
        boolean actual = dayOfMonth.checkCornValue(cronMsg);
        assertFalse(actual);
    }

    @Test
    public void checkHumanValue() {
        boolean actual = dayOfMonth.checkHumanValue(humanMsg);
        assertFalse(actual);
    }

    @Test
    public void getHumanPhrase() {
        String expected = humanMsg;
        String actual = dayOfMonth.getHumanPhrase(cronMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }

    @Test
    public void getCronPhrase() {
        String expected = cronMsg;
        String actual = dayOfMonth.getCronPhrase(humanMsg);
        assertEquals(expected, actual);
        assertNotNull(actual);
    }
}