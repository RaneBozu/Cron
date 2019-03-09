package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateHistoryResponse implements ResponseManager {
    private Request request;
    UpdateHistoryResponse(Request request) {
        this.request = request;
    }

    @Override
    public Response getResponse(Statement statement) {
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        if(response.getErrorMsg() == null) {
            try {
                statement.executeUpdate("UPDATE history SET \"OutputMsg\" = '" + response.getOutputMsg() + "', \"InputMsg\" = '" + request.getInputMsg() + "' WHERE id = " + request.getHistoryID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return response;
        } else {
            return response;
        }
    }
}
