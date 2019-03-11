package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;
import java.sql.Statement;

public class UpdateHistoryResponse implements ResponseManager {
    private Request request;

    UpdateHistoryResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to update history
     */
    @Override
    public Response getResponse(Statement statement) {
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        DataBaseRequest updateHistoryOnDB = new UpdateHistoryInDatabase();
        return updateHistoryOnDB.getResponseFromDB(request, response, statement);
    }
}