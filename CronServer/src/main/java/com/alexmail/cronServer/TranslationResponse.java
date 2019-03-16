package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

class TranslationResponse {
    private static Logger LOGGER = LogManager.getLogger(TranslationResponse.class.getSimpleName());

    /**
     * Generates a response to a history request
     */
    Response getResponse(Request request, DataBaseConnection connection) {
        Response response = new Translator(request).getResponse();
        if (response.getErrorMsg() == null) {
            String sqlQuery = "INSERT into history values (default, '" + request.getInputMsg() + "', '" + response.getOutputMsg() + "', " + request.isCronMsg() + ", default)";
            try {
                connection.update(sqlQuery);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        } else {
            LOGGER.info("Input error message received");
        }
        LOGGER.info("Message translated");
        return response;
    }
}