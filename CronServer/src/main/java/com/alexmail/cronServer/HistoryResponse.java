package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

public class HistoryResponse implements ResponseManager {
    private Request request;

    HistoryResponse(Request request) {
        this.request = request;
    }

    /**
     * Generates a response to a translation request
     */
    @Override
    public Response getResponse() {
        Response response = new Response();
        DataBaseRequest historyFromDB = new HistoryResponseFromDatabase();
        return historyFromDB.getResponseFromDB(request, response);
    }
}