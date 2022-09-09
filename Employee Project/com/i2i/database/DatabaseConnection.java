package com.i2i.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/employeedb";
    private static String userName = "root";
    private static String password = "root1234";
    private static PreparedStatement preparedStatement;

    private static Connection connection;

    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
        
    public static Connection connectDatabase() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }
}