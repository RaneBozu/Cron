package com.alexmail.cronServer;

import java.sql.*;

class DataBaseConnection {
    private static DataBaseConnection ourInstance;
    private static Statement statement;

    private DataBaseConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/History", "user", "user");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void connect() {
        if(ourInstance == null){
            synchronized (DataBaseConnection.class){
                if(ourInstance == null) {
                    ourInstance = new DataBaseConnection();
                }
            }
        }
    }

    static ResultSet query(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    static void update(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }
}