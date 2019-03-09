package com.alexmail.cronClient;

import com.alexmail.cronDTO.Request;
import com.alexmail.cronDTO.Response;

interface RequestManager {

    Response sendRequest(Request request);

}