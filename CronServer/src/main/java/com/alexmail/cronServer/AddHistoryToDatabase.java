package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class AddHistoryToDatabase implements DataBaseRequest {

    private static Logger LOGGER = LogManager.getLogger(TranslationResponse.class.getSimpleName());
    /**
     * Add History To Database
     */
    @Override
    public Response getResponseFromDB(Request request, Response response) {
        if(response.getErrorMsg() == null) {
            try {
                DataBaseConnection.update("INSERT into history values (default, '" + request.getInputMsg() + "', '" + response.getOutputMsg() + "', " + request.isCronMsg() + ", default)");
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
