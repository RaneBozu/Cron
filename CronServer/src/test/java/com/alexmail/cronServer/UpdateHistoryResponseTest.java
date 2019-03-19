package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UpdateHistoryResponseTest {

    private Translator mockTranslator;
    private DataBaseConnection mockDBConn;
    private String sql;
    private Request mockRequest;
    private Response mockResponse;
    private String outputMsg;
    private String inputMsg;

    @Before
    public void setUp() throws Exception {
        mockTranslator = mock(Translator.class);
        mockDBConn = mock(DataBaseConnection.class);
        mockRequest = mock(Request.class);
        mockResponse = mock(Response.class);
        sql = "SQL mockRequest";
        outputMsg = "1 минут(а)";
        inputMsg = "1";
    }

    @Test
    public void getResponse() throws SQLException {
        mockTranslator.getResponse();
        when(mockTranslator.getResponse()).thenReturn(mockResponse);

        mockResponse.getErrorMsg();
        when(mockResponse.getErrorMsg()).thenReturn(null);

        mockResponse.getOutputMsg();
        when(mockResponse.getOutputMsg()).thenReturn(outputMsg);

        mockRequest.getInputMsg();
        when(mockRequest.getInputMsg()).thenReturn(inputMsg);

        mockRequest.getHistoryID();
        when(mockRequest.getHistoryID()).thenReturn(1);

        mockDBConn.update(sql);

        verify(mockTranslator).getResponse();
        verify(mockResponse).getErrorMsg();
        verify(mockResponse).getOutputMsg();
        verify(mockRequest).getInputMsg();
        verify(mockRequest).getHistoryID();
        verify(mockDBConn).update(sql);

        assertEquals(mockResponse, mockTranslator.getResponse());
        assertEquals(null, mockResponse.getErrorMsg());
        assertEquals(outputMsg, mockResponse.getOutputMsg());
        assertEquals(inputMsg, mockRequest.getInputMsg());
        assertEquals(1, mockRequest.getHistoryID());
    }
}