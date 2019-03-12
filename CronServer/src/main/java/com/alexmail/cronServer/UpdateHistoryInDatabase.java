package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class UpdateHistoryInDatabase implements DataBaseRequest {

    private static Logger LOGGER = LogManager.getLogger(UpdateHistoryResponse.class.getSimpleName());

    /**
     * Update history in Database
     */
    @Override
    public Response getResponseFromDB(Request request, Response response) {
        if(response.getErrorMsg() == null) {
            try {
                DataBaseConnection.update("UPDATE history SET \"OutputMsg\" = '" + response.getOutputMsg() + "', \"InputMsg\" = '" + request.getInputMsg() + "' WHERE id = " + request.getHistoryID());
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            LOGGER.info("History updated");
            return response;
        } else {
            LOGGER.info("Input error message received");
            return response;
        }
    }
}