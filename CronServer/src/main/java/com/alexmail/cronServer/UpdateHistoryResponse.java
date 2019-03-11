package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class UpdateHistoryResponse implements ResponseManager {
    private static Logger LOGGER = LogManager.getLogger(UpdateHistoryResponse.class.getSimpleName());
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
            LOGGER.info("History updated");
            return response;
        } else {

            LOGGER.info("Input error message received");
            return response;
        }
    }
}
