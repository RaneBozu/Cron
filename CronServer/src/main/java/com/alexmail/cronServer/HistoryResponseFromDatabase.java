package com.alexmail.cronServer;

import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryResponseFromDatabase implements DataBaseRequest {
    private static Logger LOGGER = LogManager.getLogger(HistoryResponse.class.getSimpleName());

    /**
     * Received history from Database
     */
    @Override
    public Response getResponseFromDB(Request request, Response response, Statement statement) {
        List<History> historyList = new ArrayList<>();
        ResultSet resultSet;
        int numOfRecords = request.getHistoryID();
        try {
            if(request.isReverseIsSelected()) {
                resultSet = statement.executeQuery("SELECT * FROM history ORDER BY DATE DESC LIMIT " + numOfRecords);
            } else {
                resultSet = statement.executeQuery("SELECT * FROM history ORDER BY DATE LIMIT " + numOfRecords);
            }

            while (resultSet.next()) {
                History history = new History(Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getBoolean(4));
                historyList.add(history);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        response.setHistoryList(historyList);
        LOGGER.info("The history received from the database");
        return response;
    }
}
