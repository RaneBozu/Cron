package com.alexmail.cronServer;

import com.alexmail.cronDTO.History;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DataBaseConnection {
    private static Logger LOGGER = LogManager.getLogger(CronHttpServer.class.getSimpleName());
    private static DataBaseConnection connection;
    private static Statement statement;

    private DataBaseConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
            statement = conn.createStatement();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.info("Connection with the database is established");
    }

    static DataBaseConnection getConnection() {
        if(connection == null){
            synchronized (DataBaseConnection.class){
                if(connection == null) {
                    connection = new DataBaseConnection();
                }
            }
        }
        return connection;
    }

    List<History> query(String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        List<History> historyList = new ArrayList<>();
        while (resultSet.next()) {
            History history = new History(Integer.parseInt(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getBoolean(4));
            historyList.add(history);
        }
        return historyList;
    }

    void update(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }
}