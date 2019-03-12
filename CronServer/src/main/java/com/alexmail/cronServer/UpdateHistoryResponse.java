package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

public class UpdateHistoryResponse implements ResponseManager {
    private Request request;

    UpdateHistoryResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to update history
     */
    @Override
    public Response getResponse() {
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        DataBaseRequest updateHistoryOnDB = new UpdateHistoryInDatabase();
        updateHistoryOnDB.getResponseFromDB(request, response);
        return response;
    }
}