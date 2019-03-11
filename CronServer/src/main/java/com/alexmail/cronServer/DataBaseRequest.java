package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

import java.sql.Statement;

public interface DataBaseRequest {
    Response getResponseFromDB(Request request, Response response, Statement statement);
}