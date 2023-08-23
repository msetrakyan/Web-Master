package com.smartcode.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection connection;
    private static DataSource instance;
    private final String url = "jdbc:postgresql://127.0.0.1:5432/test1";
    private final String username = "postgres";
    private final String password = "postgres";
    private final String driver = "org.postgresql.Driver";

    private DataSource() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            Class.forName(driver);
            System.out.println("Connected Successfully");
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DataSource();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }
}