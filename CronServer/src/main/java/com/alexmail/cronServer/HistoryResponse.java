package com.alexmail.cronServer;

import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class HistoryResponse {
    private static Logger LOGGER = LogManager.getLogger(HistoryResponse.class.getSimpleName());

    /**
     * Generates a response to a translation request
     */
    Response getResponse(Request request, DataBaseConnection connection) {
        Response response = new Response();
        List<History> historyList = new ArrayList<>();
        String sql;
        String datePeriod = "";
        if(request.isTimePeriodSelected())
            datePeriod = "WHERE date BETWEEN '" + request.getHistoryStartDate() + "' AND '" + request.getHistoryEndDate() + "'";
        if(request.isReverseIsSelected()) {
            sql = "SELECT * FROM history " + datePeriod + " ORDER BY DATE DESC LIMIT " + request.getNumOfHistory();
        } else {
            sql = "SELECT * FROM history " + datePeriod + " ORDER BY DATE LIMIT " + request.getNumOfHistory();
        }
        try {
            historyList = connection.query(sql);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        response.setHistoryList(historyList);
        LOGGER.info("The history received from the database");
        return response;
    }
}