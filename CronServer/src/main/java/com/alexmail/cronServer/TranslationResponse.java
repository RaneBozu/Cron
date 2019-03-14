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
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        if (response.getErrorMsg() == null) {
            try {
                String sql = "INSERT into history values (default, '" + request.getInputMsg() + "', '" + response.getOutputMsg() + "', " + request.isCronMsg() + ", default)";
                connection.update(sql);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            LOGGER.info("Message translated");
        } else {
            LOGGER.info("Input error message received");
        }
        return response;
    }
}