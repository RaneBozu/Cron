package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

import java.sql.SQLException;
import java.sql.Statement;


class TranslationResponse implements ResponseManager {
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
        try {
                statement.executeUpdate("INSERT into history values (default, '" + request.getInputMsg() + "', '" + response.getOutputMsg() + "', " + request.isCronMsg() + ", default)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }
}