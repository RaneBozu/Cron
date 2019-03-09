package com.alexmail.cronServer;


import com.alexmail.cronDTO.History;
import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryResponse implements ResponseManager {
    private Request request;

    HistoryResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to a translation request
     */
    @Override
    public Response getResponse(Statement statement) {
        Response response = new Response();
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
            e.printStackTrace();
        }
        response.setHistoryList(historyList);
        return response;
    }
}
