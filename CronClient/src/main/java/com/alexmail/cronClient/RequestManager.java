package com.alexmail.cronClient;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

public interface RequestManager {

    Response sendRequest(Request request);

}