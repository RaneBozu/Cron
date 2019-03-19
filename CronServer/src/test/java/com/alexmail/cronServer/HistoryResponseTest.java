package com.alexmail.cronServer;

import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HistoryResponseTest {

    private List<History> historyList;
    private String sql;
    private DataBaseConnection mockDBConn;
    private Request mockRequest;
    private Response mockResponse;

    @Before
    public void setUp() throws Exception {
        historyList = new ArrayList<>();
        sql = "SQL mockRequest";
        mockDBConn = mock(DataBaseConnection.class);
        mockRequest = mock(Request.class);
        mockResponse = mock(Response.class);

    }

    @Test
    public void getResponse() throws SQLException {

        mockRequest.isTimePeriodSelected();
        when(mockRequest.isTimePeriodSelected()).thenReturn(true);

        mockRequest.getHistoryStartDate();
        when(mockRequest.getHistoryStartDate()).thenReturn("01.01.2019 00:00:00");

        mockRequest.getHistoryEndDate();
        when(mockRequest.getHistoryEndDate()).thenReturn("01.01.2019 23:59:59");

        mockRequest.getNumOfHistory();
        when(mockRequest.getNumOfHistory()).thenReturn(10);

        mockDBConn.query(sql);
        when(mockDBConn.query(sql)).thenReturn(new ArrayList<>());

        mockResponse.setHistoryList(historyList);

        verify(mockRequest).isTimePeriodSelected();
        verify(mockRequest).getHistoryStartDate();
        verify(mockRequest).getHistoryEndDate();
        verify(mockRequest).getNumOfHistory();
        verify(mockDBConn).query(sql);
        verify(mockResponse).setHistoryList(historyList);
        assertTrue(mockRequest.isTimePeriodSelected());
        assertEquals("01.01.2019 00:00:00", mockRequest.getHistoryStartDate());
        assertEquals("01.01.2019 23:59:59", mockRequest.getHistoryEndDate());
        assertEquals(10, mockRequest.getNumOfHistory());
        assertEquals(new ArrayList<>(), mockDBConn.query(sql));
    }
}