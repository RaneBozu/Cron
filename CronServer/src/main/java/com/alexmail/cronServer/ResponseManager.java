package com.alexmail.cronServer;

import com.alexmail.cronDTO.Response;

import java.sql.Statement;

public interface ResponseManager {
    Response getResponse(Statement statement);
}