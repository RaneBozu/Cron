package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

class UpdateHistoryResponse {
    private static Logger LOGGER = LogManager.getLogger(UpdateHistoryResponse.class.getSimpleName());

    /**
     * Generates a response to update history
     */
    Response getResponse(Request request, DataBaseConnection connection) {
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        if (response.getErrorMsg() == null) {
            try {
                String sql = "UPDATE history SET \"OutputMsg\" = '" + response.getOutputMsg() + "', \"InputMsg\" = '" + request.getInputMsg() + "' WHERE id = " + request.getHistoryID();
                connection.update(sql);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            LOGGER.info("History updated");
        } else {
            LOGGER.info("Input error message received");
        }
        return response;
    }
}