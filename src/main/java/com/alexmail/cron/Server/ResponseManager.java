package com.alexmail.cron.Server;

import com.alexmail.cron.DTO.Request;
import com.alexmail.cron.DTO.RequestType;
import com.alexmail.cron.DTO.Response;

class ResponseManager {

    private Response response = new Response();
    private History history = History.getInstance();

    Response getResponse() {
        return response;
    }

    void formingResponse(Request request) {

        if (request.getRequestType().equals(RequestType.TRANSLATE)) {

            Translator translator = new Translator(request, response);
            response = translator.getResponse();
            history.addHistory(request, response);

        } else {
            response.setHistoryList(history.getHistoryList());
        }
    }
}