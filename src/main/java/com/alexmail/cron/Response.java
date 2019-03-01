package com.alexmail.cron;

import java.io.IOException;

class Response {

    private History history = History.getInstance();

    byte[] getResponse(Request request) throws IOException {
        if (request.getHeader().equals("Translate")) {
            Translator translator = new Translator(request);
            request.setOutputMsg(translator.getTranslate());
            history.addHistory(request);
        } else {
            request.setHistory(history.getHistoryList());
        }
        return Serializer.serialize(request);
    }
}