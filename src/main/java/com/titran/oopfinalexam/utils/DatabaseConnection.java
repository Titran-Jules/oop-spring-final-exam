package com.titran.oopfinalexam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private DatabaseConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                var props = ConfigLoader.load();
                connection = DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.user"),
                        props.getProperty("db.password")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
