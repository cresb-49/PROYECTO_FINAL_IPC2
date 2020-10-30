package com.mycompany.proyecto_final.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection connection = null;
    private static ConnectDB connectDB;

    private ConnectDB() {
        String url = "jdbc:mysql://localhost:3306/NOTAS?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "ex=d/dx=ex";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Error DB: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (connectDB == null) {
            connectDB = new ConnectDB();
        }
        return connection;
    }
}
