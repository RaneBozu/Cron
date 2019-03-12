package com.alexmail.cronServer;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

public interface DataBaseRequest {
    Response getResponseFromDB(Request request, Response response);
}