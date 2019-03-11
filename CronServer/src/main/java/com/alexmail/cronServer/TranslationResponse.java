package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

class TranslationResponse implements ResponseManager {
    private static Logger LOGGER = LogManager.getLogger(TranslationResponse.class.getSimpleName());
    private Request request;

    TranslationResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to a history request
     */
    @Override
    public Response getResponse(Statement statement) {

        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        if(response.getErrorMsg() == null) {
            try {
                statement.executeUpdate("INSERT into history values (default, '" + request.getInputMsg() + "', '" + response.getOutputMsg() + "', " + request.isCronMsg() + ", default)");
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            LOGGER.info("Message translated");
            return response;
        } else {
            LOGGER.info("Input error message received");
            return response;
        }
    }
}