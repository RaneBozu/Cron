package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

class TranslationResponse implements ResponseManager {
    private Request request;

    TranslationResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to a history request
     */
    @Override
    public Response getResponse() {
        Translator translator = new Translator(request);
        Response response = translator.getResponse();
        DataBaseRequest addHistory = new AddHistoryToDatabase();
        addHistory.getResponseFromDB(request, response);
        return response;
    }
}