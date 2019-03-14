package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

class ResponseManager {
    Response getResponse(Request request, DataBaseConnection connection){

        switch (request.getRequestType()){
            case HISTORY:
                return new HistoryResponse().getResponse(request, connection);
            case TRANSLATE:
                return new TranslationResponse().getResponse(request, connection);
            case UPDATE_HISTORY:
                return new UpdateHistoryResponse().getResponse(request, connection);
                default: throw new IllegalArgumentException();
        }
    }
}